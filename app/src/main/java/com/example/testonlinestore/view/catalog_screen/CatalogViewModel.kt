package com.example.testonlinestore.view.catalog_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.presentation.use_case.catalog.GetCatalogProductUseCase
import com.example.testonlinestore.presentation.use_case.catalog.GetImageProductUseCase
import com.example.testonlinestore.presentation.use_case.catalog.GetProductsSortedByPopularityUseCase
import com.example.testonlinestore.presentation.use_case.catalog.GetProductsSortedByPriceHighToLowUseCase
import com.example.testonlinestore.presentation.use_case.catalog.GetProductsSortedByPriceLowToHighUseCase
import com.example.testonlinestore.presentation.use_case.catalog.GetTagsUseCase
import com.example.testonlinestore.utils.Resource
import com.example.testonlinestore.utils.SortOption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getCatalogProduct : GetCatalogProductUseCase,
    private val getTagsUseCase : GetTagsUseCase,
    private val getProductsSortedByPopularityUseCase : GetProductsSortedByPopularityUseCase,
    private val getProductsSortedByPriceLowToHighUseCase : GetProductsSortedByPriceLowToHighUseCase,
    private val getProductsSortedByPriceHighToLowUseCase : GetProductsSortedByPriceHighToLowUseCase,
    private val getImageProductUseCase : GetImageProductUseCase
) : ViewModel() {

    private var _listProduct = MutableStateFlow<Resource<CatalogList>>(Resource.Loading())
    val listProduct : StateFlow<Resource<CatalogList>> = _listProduct


    private var _tags = MutableStateFlow<Resource<List<String>>>(Resource.Loading())
    val tags : StateFlow<Resource<List<String>>> = _tags


    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {

            try {

                val catalogList = getCatalogProduct()
                _listProduct.value = Resource.Success(catalogList)
            } catch (e : Exception) {
                _listProduct.value = Resource.Error(e.message ?: "An error occurred")
            }

        }
    }

    fun sortCatalogProducts(sortOption : SortOption) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sortedProducts = when (sortOption) {
                    SortOption.POPULARITY -> {
                        getProductsSortedByPopularityUseCase(
                            _listProduct.value.data ?: CatalogList(
                                emptyList()
                            )
                        )
                    }

                    SortOption.PRICE_LOW_TO_HIGH -> {
                        getProductsSortedByPriceLowToHighUseCase(
                            _listProduct.value.data ?: CatalogList(emptyList())
                        )
                    }

                    SortOption.PRICE_HIGH_TO_LOW -> {
                        getProductsSortedByPriceHighToLowUseCase(_listProduct.value.data ?: CatalogList(
                            emptyList()
                        )
                        )
                    }
                }
                _listProduct.value = Resource.Success(CatalogList(sortedProducts))
            } catch (e : Exception) {
                _listProduct.value = Resource.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun getImageProducts() : List<ImageProduct> {

        return getImageProductUseCase()
    }

    // todo Доделать фильтрацию карусели
    fun filterCatalogByTags(selectedTags : Set<String>) {
        viewModelScope.launch {
            try {
                val catalogResource = _listProduct.value
                if (catalogResource is Resource.Success) {
                    val catalog = catalogResource.data
                    val filteredCatalog = catalog?.items?.filter { product ->
                        product.tags.any { selectedTags.contains(it) }
                    }
                    _listProduct.value = Resource.Success(filteredCatalog?.let { CatalogList(it) })
                }
            } catch (e : Exception) {
                _listProduct.value = Resource.Error(e.message ?: "An error occurred")
            }
        }

    }


}