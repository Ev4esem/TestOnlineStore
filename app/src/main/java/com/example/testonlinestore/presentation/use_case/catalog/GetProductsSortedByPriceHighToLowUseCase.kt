package com.example.testonlinestore.presentation.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.presentation.repository.CatalogRepository
import javax.inject.Inject

class GetProductsSortedByPriceHighToLowUseCase @Inject constructor (
    private val catalogRepository : CatalogRepository
) {

    operator fun invoke(catalogList : CatalogList) : List<Catalog> {
        return catalogRepository.getProductsSortedByPriceHighToLow(catalogList.items)
    }

}