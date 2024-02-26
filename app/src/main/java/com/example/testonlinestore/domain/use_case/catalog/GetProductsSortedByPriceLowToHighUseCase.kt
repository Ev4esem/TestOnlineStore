package com.example.testonlinestore.domain.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsSortedByPriceLowToHighUseCase @Inject constructor (
    private val catalogRepository : CatalogRepository
) {

    suspend operator fun invoke(catalogList : List<Catalog>) : List<Catalog> {
        return catalogRepository.getProductsSortedByPriceLowToHigh(catalogList)
    }

}