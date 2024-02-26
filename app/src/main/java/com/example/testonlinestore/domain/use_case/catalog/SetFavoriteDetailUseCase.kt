package com.example.testonlinestore.domain.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogDetail
import com.example.testonlinestore.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetFavoriteDetailUseCase @Inject constructor(
    private val catalogRepository : CatalogRepository
) {

    operator fun invoke(catalog : CatalogDetail) : Flow<Boolean> {
        return catalogRepository.setFavoriteDetail(catalog)
    }

}