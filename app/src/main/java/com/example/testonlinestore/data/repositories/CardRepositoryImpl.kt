package com.example.testonlinestore.data.repositories

import com.example.testonlinestore.data.database.dao.CardDao
import com.example.testonlinestore.data.database.models.CardItem
import com.example.testonlinestore.data.database.models.toCatalog
import com.example.testonlinestore.di.IoDispatcher
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.repository.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao : CardDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CardRepository {

    override  fun getAllListCardItem() : Flow<List<Catalog>> = cardDao.getAllCardItemsFlow().map {
        it.map(CardItem::toCatalog)
    }.flowOn(ioDispatcher)

}