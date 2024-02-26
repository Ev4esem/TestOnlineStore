package com.example.testonlinestore.domain.use_case.catalog

import com.example.testonlinestore.domain.model.catalog.CatalogDetail
import com.example.testonlinestore.domain.repository.CatalogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val catalogRepository : CatalogRepository
) {

    operator fun invoke(id : String) : Flow<CatalogDetail> {
        return catalogRepository.getFilmDetail(id)
    }

}