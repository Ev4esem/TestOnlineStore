package com.example.testonlinestore.domain.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testonlinestore.domain.model.favorite.CardItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCardItem(cardItem : CardItem)

    @Query("SELECT * FROM card_item")
    suspend fun getAllCardItemsFlow() : List<CardItem>


    @Delete
    suspend fun deleteCardItem(cardItem : CardItem)


}