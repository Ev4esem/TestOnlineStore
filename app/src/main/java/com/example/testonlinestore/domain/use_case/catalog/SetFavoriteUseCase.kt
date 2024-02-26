package com.example.testonlinestore.domain.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor (
   private val catalogRepository : CatalogRepository
) {

    operator fun invoke(catalog : Catalog) : Flow<Boolean> {
        return catalogRepository.setFavorite(catalog)
    }

}