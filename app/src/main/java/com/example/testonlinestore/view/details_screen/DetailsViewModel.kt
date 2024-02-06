package com.example.testonlinestore.view.details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.presentation.use_case.details.GetCatalogByIdUseCase
import com.example.testonlinestore.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCatalogByIdUseCase : GetCatalogByIdUseCase
) : ViewModel() {

    fun getCatalogById(
        catalogId : String,
        result: (Resource<Catalog>) -> Unit
    ) {
        viewModelScope.launch {
          try {
              result(Resource.Loading())
              val catalogItem = getCatalogByIdUseCase(catalogId)
              if (catalogItem != null) {
                  result(Resource.Success(catalogItem))
              } else {
                  result(Resource.Error("Product not found"))
              }
          } catch (e : Exception) {
              result(Resource.Error(e.message ?: "An error occurred"))
          }

        }
    }

}