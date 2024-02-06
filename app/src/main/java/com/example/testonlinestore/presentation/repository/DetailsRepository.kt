package com.example.testonlinestore.presentation.repository

import com.example.testonlinestore.domain.model.catalog.Catalog

interface DetailsRepository {

    suspend fun getCatalogById(catalogId : String) : Catalog?

}