package com.example.testonlinestore.domain.repositories

import com.example.testonlinestore.R
import com.example.testonlinestore.domain.mapper.toCatalogListProduct
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.domain.retrofit.CatalogAPI
import com.example.testonlinestore.presentation.repository.CatalogRepository
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor (private val catalogAPI : CatalogAPI) : CatalogRepository {
    override suspend fun getCatalogProduct() : CatalogList {
        return catalogAPI.getProducts().body()?.toCatalogListProduct() ?: CatalogList(listOf())
    }


    override suspend fun getTags(catalog : Catalog) : List<String> {
        return catalog.tags
    }

    override  fun getImageProduct() : List<ImageProduct> {

        return listOf(
            ImageProduct(
                image = R.drawable.image_product
            ),
            ImageProduct(
                image = R.drawable.image_product4
            ),
            ImageProduct(
                image = R.drawable.image_product2
            ),
            ImageProduct(
                image = R.drawable.image_product3
            ),
        )

    }

    override fun getProductsSortedByPopularity(products : List<Catalog>) : List<Catalog> {
        return products.sortedByDescending { it.feedback.rating }
    }

    override fun getProductsSortedByPriceLowToHigh(products : List<Catalog>) : List<Catalog> {
       return products.sortedBy { it.price.priceWithDiscount.toDoubleOrNull() ?: Double.MAX_VALUE }
    }

    override fun getProductsSortedByPriceHighToLow(products : List<Catalog>) : List<Catalog> {
        return products.sortedByDescending { it.price.priceWithDiscount.toDoubleOrNull() ?: Double.MIN_VALUE }
    }
}