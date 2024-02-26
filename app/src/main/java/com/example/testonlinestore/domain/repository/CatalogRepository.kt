package com.example.testonlinestore.domain.repository

import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogDetail
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.domain.model.catalog.Tags
import kotlinx.coroutines.flow.Flow

interface CatalogRepository {

    suspend fun getCatalogProduct() : Flow<List<Catalog>>

    suspend fun getTags(products : List<Catalog>,tag : String) : List<Catalog>

     fun getImageProduct() : List<ImageProduct>

     fun setFavorite(
         catalog : Catalog
     ) : Flow<Boolean>

    fun setFavoriteDetail(
        catalog : CatalogDetail
    ) : Flow<Boolean>

     fun getFilmDetail(id : String) : Flow<CatalogDetail>

    suspend fun getProductsSortedByPopularity(products: List<Catalog>): List<Catalog>
    suspend fun getProductsSortedByPriceLowToHigh(products: List<Catalog>): List<Catalog>
    suspend fun getProductsSortedByPriceHighToLow(products: List<Catalog>): List<Catalog>
}