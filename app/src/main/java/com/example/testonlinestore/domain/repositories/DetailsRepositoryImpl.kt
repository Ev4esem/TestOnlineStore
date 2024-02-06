package com.example.testonlinestore.domain.repositories

import com.example.testonlinestore.domain.mapper.toCatalogListProduct
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.retrofit.CatalogAPI
import com.example.testonlinestore.presentation.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(private val catalogAPI : CatalogAPI) : DetailsRepository {


    override suspend fun getCatalogById(catalogId : String) : Catalog? {
        val productList = catalogAPI.getProducts().body()?.toCatalogListProduct()?.items
        return productList?.find { it.id == catalogId }
    }


}