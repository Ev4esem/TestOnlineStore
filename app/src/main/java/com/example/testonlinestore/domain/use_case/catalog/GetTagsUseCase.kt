package com.example.testonlinestore.domain.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.repository.CatalogRepository
import javax.inject.Inject


class GetTagsUseCase @Inject constructor(private val catalogRepository : CatalogRepository) {

    suspend operator fun invoke(product : List<Catalog>,tag : String) : List<Catalog> {
        return catalogRepository.getTags(product,tag)
    }

}