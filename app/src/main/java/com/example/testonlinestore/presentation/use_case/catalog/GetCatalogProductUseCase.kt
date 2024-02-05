package com.example.testonlinestore.presentation.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.presentation.repository.CatalogRepository
import javax.inject.Inject

class GetCatalogProductUseCase @Inject constructor(
   private val catalogRepository : CatalogRepository
) {

    suspend operator fun invoke() : CatalogList {
        return catalogRepository.getCatalogProduct()
    }

}