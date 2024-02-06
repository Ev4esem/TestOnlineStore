package com.example.testonlinestore.presentation.use_case.details

import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.presentation.repository.DetailsRepository
import javax.inject.Inject

class GetCatalogByIdUseCase @Inject constructor(private val detailsRepository : DetailsRepository) {

    suspend operator fun invoke(catalogId : String) : Catalog? {
        return detailsRepository.getCatalogById(catalogId)
    }

}