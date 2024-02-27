package com.example.testonlinestore.view.catalog_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogDetail
import com.example.testonlinestore.domain.use_case.catalog.GetCatalogProductUseCase
import com.example.testonlinestore.domain.use_case.catalog.GetImageProductUseCase
import com.example.testonlinestore.domain.use_case.catalog.GetProductDetailUseCase
import com.example.testonlinestore.domain.use_case.catalog.GetProductsSortedByPopularityUseCase
import com.example.testonlinestore.domain.use_case.catalog.GetProductsSortedByPriceHighToLowUseCase
import com.example.testonlinestore.domain.use_case.catalog.GetProductsSortedByPriceLowToHighUseCase
import com.example.testonlinestore.domain.use_case.catalog.GetTagsUseCase
import com.example.testonlinestore.domain.use_case.catalog.SetFavoriteDetailUseCase
import com.example.testonlinestore.domain.use_case.catalog.SetFavoriteUseCase
import com.example.testonlinestore.utils.EffectHandler
import com.example.testonlinestore.utils.EventHandler
import com.example.testonlinestore.utils.SortOption
import com.example.testonlinestore.utils.collectAsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getCatalogProduct : GetCatalogProductUseCase,
    private val getTagsUseCase : GetTagsUseCase,
    private val setFavoriteDetailUseCase : SetFavoriteDetailUseCase,
    private val getProductDetailUSeCase : GetProductDetailUseCase,
    private val setFavoriteUseCase : SetFavoriteUseCase,
    private val getProductsSortedByPopularityUseCase : GetProductsSortedByPopularityUseCase,
    private val getProductsSortedByPriceLowToHighUseCase : GetProductsSortedByPriceLowToHighUseCase,
    private val getProductsSortedByPriceHighToLowUseCase : GetProductsSortedByPriceHighToLowUseCase,
    private val getImageProductUseCase : GetImageProductUseCase
) : ViewModel(), EffectHandler<CatalogEffect>, EventHandler<CatalogEvent> {

    private var _productsUiState = MutableStateFlow(CatalogUiState())
    val productsUiState  = _productsUiState.asStateFlow()



    override val effectChannel : Channel<CatalogEffect> = Channel()

    override fun obtainEvent(event : CatalogEvent) {
        when(event) {
            is CatalogEvent.ChangeFavorite -> {
                changeFavorite(event.catalog)
            }
            CatalogEvent.RefreshData -> {
                getProducts()
            }

            is CatalogEvent.SelectedProduct -> {
                getProductDetail(event.id)
            }

            is CatalogEvent.ClearSelectedCatalog -> {
                clearSelectedProduct()
            }
            is CatalogEvent.SelectedTag -> {
                filterCatalogByTags(event.tags,event.tag)
            }
            is CatalogEvent.SelectSortCatalog -> {
                sortCatalogProducts(event.option)
            }
            is CatalogEvent.RefreshProductDetail -> {
                getProductDetail(event.id)
            }
            is CatalogEvent.ChangeFavoriteDetail -> {
                changeFavoriteDetail(event.catalog)
            }
        }
    }

    init {
        getProducts()
        getImageProducts()
    }

    private fun clearSelectedProduct() {
        _productsUiState.update { currentState ->

            currentState.copy(
                catalogDetailLoading = false,
                errorCatalogDetail = null,
                selectedProduct = null
            )

        }
    }

    private fun changeFavorite(catalog : Catalog) {
        viewModelScope.launch {
            setFavoriteUseCase(catalog).collectAsResult(
                onSuccess = { newValue ->
                    _productsUiState.update {  currentState ->
                        currentState.copy(
                            products = currentState.products.toMutableList()
                                .map { if (it.id == catalog.id) it.copy(favorite = newValue) else it }
                        )
                    }
                }
            )
        }
    }
    // todo Create DetailViewModel
    private fun changeFavoriteDetail(catalog : CatalogDetail) {
        viewModelScope.launch {
            setFavoriteDetailUseCase(catalog).collectAsResult(
                onSuccess = { newValue ->
                    _productsUiState.update {  currentState ->
                        currentState.copy(
                            products = currentState.products.toMutableList()
                                .map { if (it.id == catalog.id) it.copy(favorite = newValue) else it }
                        )
                    }
                }
            )
        }
    }
    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {

            getCatalogProduct().collectAsResult(
                onSuccess = { products ->
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            products = products,
                            catalogLoading = false,
                            error = null
                        )
                    }
                },
                onError = { ex, message ->
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            catalogLoading = false,
                            error = message
                        )
                    }
                },
                onLoading = {
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            catalogLoading = true,
                            error = null
                        )
                    }
                }
            )

        }
    }

    fun sortCatalogProducts(sortOption : SortOption) {
        viewModelScope.launch(Dispatchers.IO) {

            getCatalogProduct().collectAsResult(
                onSuccess = { catalog ->

                    val sortedCatalog = when(sortOption) {
                        SortOption.POPULARITY -> getProductsSortedByPopularityUseCase(catalog)
                        SortOption.PRICE_HIGH_TO_LOW -> getProductsSortedByPriceHighToLowUseCase(catalog)
                        SortOption.PRICE_LOW_TO_HIGH -> getProductsSortedByPriceLowToHighUseCase(catalog)
                    }

                    _productsUiState.update { currentState ->
                        currentState.copy(
                            sortOption = sortOption,
                            sortCatalogList = sortedCatalog,
                            catalogLoading = false,
                            error = null
                        )
                    }
                },
                onError = { ex,message ->
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            catalogLoading = false,
                            error = message
                        )
                    }
                },
                onLoading = {
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            catalogLoading = true
                        )
                    }
                }
            )
        }
    }


    fun getProductDetail(id : String) {
        viewModelScope.launch {
            getProductDetailUSeCase(
                id = id
            ).collectAsResult(
                onSuccess = { productDetail ->

                    _productsUiState.update { currentState ->

                        currentState.copy(
                            catalogDetailLoading = false,
                            errorCatalogDetail = null,
                            selectedProduct = productDetail
                        )

                    }

                },
                onError = { ex, message ->
                    _productsUiState.update {  currentState ->
                        currentState.copy(
                            catalogDetailLoading = false,
                            errorCatalogDetail = message
                        )
                    }
                    sendEffect(CatalogEffect.ShowToast(message.toString()))
                },
                onLoading = {
                    _productsUiState.update { currentState ->

                        currentState.copy(
                            catalogDetailLoading = true
                        )

                    }
                }
            )
        }
    }

    fun getImageProducts() {
        _productsUiState.update { currentState ->
            currentState.copy(
                imageProducts = getImageProductUseCase()
            )
        }
    }

    fun filterCatalogByTags(products : List<Catalog>,tag : String) {
        viewModelScope.launch {

            getCatalogProduct().collectAsResult(
                onSuccess = { catalog ->
                     val filterTags = getTagsUseCase(products,tag)
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            tags = filterTags,
                            selectedTag = tag,
                            catalogLoading = false,
                            error = null
                        )
                    }
                },
                onError = { ex, message ->
                    _productsUiState.update {  currentState ->
                        currentState.copy(
                            catalogLoading = false,
                            error = message
                        )
                    }
                },
                onLoading = {
                    _productsUiState.update {  currentState ->
                        currentState.copy(
                            catalogLoading = true,
                        )
                    }
                }

            )

        }

    }



}

/**
 * Все State которые могут быть
 */
data class CatalogUiState(
    val catalogLoading : Boolean = false,
    val favoriteCatalogLoading : Boolean = false,
    val catalogDetailLoading : Boolean = false,
    val error : String? = null,
    val imageProducts : List<ImageProduct> = listOf(),
    val tags : List<Catalog> = emptyList(),
    val errorCatalogDetail : String? = null,
    var selectedTag : String = "",
    val sortOption : SortOption = SortOption.POPULARITY,
    val sortCatalogList : List<Catalog> = emptyList(),
    val products : List<Catalog> = emptyList(),
    val favoriteCatalog : List<Catalog> = emptyList(),
    val selectedProduct : CatalogDetail? = null
)

sealed interface CatalogEffect {
    data class ShowToast(
        val message : String
    ) : CatalogEffect
}

/**
 * Обработка событий на экране
 * 1)Изменение избранного
 * 2)Выбранный продукт
 */
sealed interface CatalogEvent {

    object RefreshData : CatalogEvent

    data class ChangeFavorite(
        val catalog : Catalog
    ) : CatalogEvent
    data class ChangeFavoriteDetail(
        val catalog : CatalogDetail
    ) : CatalogEvent

    data class SelectedProduct(
        val id : String
    ) : CatalogEvent

    data class SelectedTag(
        val tags : List<Catalog>,
        val tag : String
    ) : CatalogEvent

    data class SelectSortCatalog(
        val option : SortOption
    ) : CatalogEvent

    data class RefreshProductDetail(val id : String) : CatalogEvent


    object ClearSelectedCatalog : CatalogEvent

}





