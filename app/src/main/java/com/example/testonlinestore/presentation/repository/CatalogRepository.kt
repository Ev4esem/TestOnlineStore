package com.example.testonlinestore.presentation.repository

import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogList

interface CatalogRepository {

    suspend fun getCatalogProduct() : CatalogList
    suspend fun getTags(catalog : Catalog) : List<String>

     fun getImageProduct() : List<ImageProduct>

    fun getProductsSortedByPopularity(products: List<Catalog>): List<Catalog>
    fun getProductsSortedByPriceLowToHigh(products: List<Catalog>): List<Catalog>
    fun getProductsSortedByPriceHighToLow(products: List<Catalog>): List<Catalog>
}