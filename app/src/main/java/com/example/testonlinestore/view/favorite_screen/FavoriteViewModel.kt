package com.example.testonlinestore.view.favorite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogDetail
import com.example.testonlinestore.domain.use_case.catalog.GetProductDetailUseCase
import com.example.testonlinestore.domain.use_case.catalog.SetFavoriteDetailUseCase
import com.example.testonlinestore.domain.use_case.catalog.SetFavoriteUseCase
import com.example.testonlinestore.domain.use_case.favorite.GetItemCardListUseCase
import com.example.testonlinestore.utils.EffectHandler
import com.example.testonlinestore.utils.EventHandler
import com.example.testonlinestore.utils.collectAsResult
import com.example.testonlinestore.view.catalog_screen.CatalogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getItemCardListUseCase : GetItemCardListUseCase,
    private val setFavoriteUseCase : SetFavoriteUseCase,
    private val getProductDetailUSeCase : GetProductDetailUseCase,
    private val setFavoriteDetailUseCase : SetFavoriteDetailUseCase,

) : ViewModel(), EffectHandler<FavoriteEffect>, EventHandler<FavoriteEvent> {

    private var _productsUiState = MutableStateFlow(FavoriteUiState())
    val productsUiState = _productsUiState.asStateFlow()

    override val effectChannel : Channel<FavoriteEffect> = Channel()

    override fun obtainEvent(event : FavoriteEvent) {
        when (event) {
            is FavoriteEvent.ChangeFavorite -> {
                changeFavorite(event.catalog)
            }

            is FavoriteEvent.ChangeFavoriteDetail -> {
                changeFavoriteDetail(event.catalog)
            }
            is FavoriteEvent.SelectedProduct -> {
                getProductDetail(event.id)
            }
            is FavoriteEvent.RefreshProductDetail -> {
                getProductDetail(event.id)
            }
            is FavoriteEvent.ClearSelectedCatalog -> {
                clearSelectedProduct()
            }
        }
    }

    init {
        getCardProducts()
    }

    private fun getCardProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getItemCardListUseCase().collectAsResult(
                onSuccess = { cardList ->
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            favoriteList = cardList,
                            productLoading = false,
                            error = null
                        )
                    }
                },
                onError = { ex, message ->
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            productLoading = false,
                            error = message
                        )
                    }
                },
                onLoading = {
                    _productsUiState.update { currentState ->

                        currentState.copy(
                            productLoading = true,
                            error = null
                        )

                    }
                }
            )
        }
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

    private fun changeFavoriteDetail(catalog : CatalogDetail) {
        viewModelScope.launch {
            setFavoriteDetailUseCase(catalog).collectAsResult(
                onSuccess = { newValue ->
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            favoriteList = currentState.favoriteList.toMutableList()
                                .map { if (it.id == catalog.id) it.copy(favorite = newValue) else it }
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
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            catalogDetailLoading = false,
                            errorCatalogDetail = message
                        )
                    }
                    sendEffect(FavoriteEffect.ShowToast(message.toString()))
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

    private fun changeFavorite(catalog : Catalog) {
        viewModelScope.launch {
            setFavoriteUseCase(catalog).collectAsResult(
                onSuccess = { newValue ->
                    _productsUiState.update { currentState ->
                        currentState.copy(
                            favoriteList = currentState.favoriteList.toMutableList()
                                .map { if (it.id == catalog.id) it.copy(favorite = newValue) else it }
                        )
                    }
                }
            )
        }
    }


}

data class FavoriteUiState(
    val productLoading : Boolean = false,
    val favoriteList : List<Catalog> = emptyList(),
    val selectedProduct : CatalogDetail? = null,
    val errorCatalogDetail : String? = null,
    val catalogDetailLoading : Boolean = false,
    val error : String? = null
)

sealed interface FavoriteEffect {
    data class ShowToast(
        val message : String
    ) : FavoriteEffect
}

sealed interface FavoriteEvent {
    data class SelectedProduct(
        val id : String
    ) : FavoriteEvent

    data class RefreshProductDetail(val id : String) : FavoriteEvent

    data class ChangeFavorite(
        val catalog : Catalog
    ) : FavoriteEvent

    data class ChangeFavoriteDetail(
        val catalog : CatalogDetail
    ) : FavoriteEvent

    object ClearSelectedCatalog : FavoriteEvent

}