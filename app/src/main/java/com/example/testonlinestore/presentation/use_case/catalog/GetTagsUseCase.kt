package com.example.testonlinestore.presentation.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.presentation.repository.CatalogRepository
import javax.inject.Inject


class GetTagsUseCase @Inject constructor(private val catalogRepository : CatalogRepository) {

    suspend operator fun invoke(catalog : Catalog) : List<String> {
        return catalogRepository.getTags(catalog)
    }

}