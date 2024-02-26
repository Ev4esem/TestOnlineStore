package com.example.testonlinestore.domain.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.domain.repository.CatalogRepository
import com.example.testonlinestore.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatalogProductUseCase @Inject constructor(
   private val catalogRepository : CatalogRepository
) {

    suspend operator fun invoke() : Flow<List<Catalog>> {
        return catalogRepository.getCatalogProduct()
    }

}