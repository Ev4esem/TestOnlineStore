package com.example.testonlinestore.domain.repositories

import com.example.testonlinestore.domain.database.CardDao
import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.presentation.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao : CardDao
) : CardRepository {

    override suspend fun addItemCard(cardItem : CardItem) {
        return cardDao.addCardItem(cardItem)
    }

    override suspend fun getAllListCardItem() : List<CardItem> {
        return cardDao.getAllCardItemsFlow()
    }

    override suspend fun deleteCardItem(cardItem : CardItem) {
        return cardDao.deleteCardItem(cardItem)
    }


}