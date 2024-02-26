package com.example.testonlinestore.data.repositories

import com.example.testonlinestore.R
import com.example.testonlinestore.data.database.dao.CardDao
import com.example.testonlinestore.data.retrofit.CatalogAPI
import com.example.testonlinestore.di.IoDispatcher
import com.example.testonlinestore.domain.mapper.toCatalogDetail
import com.example.testonlinestore.domain.mapper.toCatalogProduct
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogDetail
import com.example.testonlinestore.domain.model.catalog.toCardItem
import com.example.testonlinestore.domain.repository.CatalogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor (
    private val catalogAPI : CatalogAPI,
    private val dao : CardDao,
    @IoDispatcher private val ioDispatcher : CoroutineDispatcher
) : CatalogRepository {

    override suspend fun getCatalogProduct() : Flow<List<Catalog>> = flow {
       val catalogsDto = catalogAPI.getProducts()
       val catalog = catalogsDto.items
           .map { catalogDto ->
               val favorite = dao.findCatalogById(catalogDto.id)?.favorite ?: false
               catalogDto.toCatalogProduct(favorite)
           }
        emit(catalog)
    }.flowOn(ioDispatcher)


    override suspend fun getTags(products : List<Catalog>, tag : String) : List<Catalog> {

        val filteredTags = products.filter { product ->
            product.tags.contains(tag)
        }
        return filteredTags
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


    /**
     * Удаляет если [true] добавляет если [false]
     */
    override fun setFavorite(catalog : Catalog) : Flow<Boolean> = flow {
        when(catalog.favorite) {
            true -> dao.deleteCardItem(catalog.id)
            false -> dao.addCardItem(catalog.toCardItem(true))
        }
        emit(!catalog.favorite)
    }.flowOn(ioDispatcher)

    override fun setFavoriteDetail(catalog : CatalogDetail) : Flow<Boolean> = flow {
        when(catalog.favorite) {
            true -> dao.deleteCardItem(catalog.id)
            false -> dao.addCardItem(catalog.toCardItem(true))
        }
        emit(!catalog.favorite)
    }.flowOn(ioDispatcher)


    override  fun getFilmDetail(id : String) : Flow<CatalogDetail> = flow {
        val catalogDetailDto = catalogAPI.getProductById(id)
        val favorite = dao.findCatalogById(id)?.favorite ?: false
        val catalogDetail = catalogDetailDto.toCatalogDetail(favorite)
        emit(catalogDetail)
    }.flowOn(ioDispatcher)

    override suspend fun getProductsSortedByPopularity(products : List<Catalog>) : List<Catalog>  {
        val sortedList = products.sortedByDescending { it.feedback.rating }
        return sortedList
    }

    override suspend fun getProductsSortedByPriceLowToHigh(products : List<Catalog>) : List<Catalog>  {
        val sortedList =  products.sortedBy { it.price.priceWithDiscount.toDoubleOrNull() ?: Double.MAX_VALUE }
        return sortedList
    }

    override suspend fun getProductsSortedByPriceHighToLow(products : List<Catalog>) : List<Catalog> {
         val sortedList = products.sortedByDescending { it.price.priceWithDiscount.toDoubleOrNull() ?: Double.MIN_VALUE }
        return sortedList
    }
}