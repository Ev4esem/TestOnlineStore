package com.example.testonlinestore.presentation.use_case.catalog

import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.presentation.repository.CatalogRepository
import javax.inject.Inject

class GetImageProductUseCase @Inject constructor(
    private val catalogRepository : CatalogRepository
) {

     operator fun invoke() : List<ImageProduct> {
        return catalogRepository.getImageProduct()
    }

}