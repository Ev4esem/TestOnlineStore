package com.example.testonlinestore.presentation.repository

import com.example.testonlinestore.domain.model.favorite.CardItem

interface CardRepository {

    suspend fun addItemCard(cardItem : CardItem)

    suspend fun getAllListCardItem() : List<CardItem>

    suspend fun deleteCardItem(cardItem : CardItem)
}