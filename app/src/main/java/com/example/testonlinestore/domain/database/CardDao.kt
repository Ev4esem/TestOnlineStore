package com.example.testonlinestore.domain.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testonlinestore.domain.model.favorite.CardItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert
    fun addCardItem(cardItem : CardItem)

    @Query("SELECT * FROM card_item")
    fun getAllCardItemsFlow() : Flow<List<CardItem>>

}