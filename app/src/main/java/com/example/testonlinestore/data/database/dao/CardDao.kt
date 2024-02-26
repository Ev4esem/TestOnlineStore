package com.example.testonlinestore.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testonlinestore.data.database.models.CardItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCardItem(cardItem : CardItem)

    @Query("SELECT * FROM card_item WHERE favorite == 1")
    fun getAllCardItemsFlow() : Flow<List<CardItem>>

    // todo
    @Query("SELECT * FROM card_item")
    suspend fun getAllCardItems() : List<CardItem>


    @Query("DELETE FROM card_item WHERE id = :cardId")
    suspend fun deleteCardItem(cardId : String)

    @Query("SELECT * FROM card_item WHERE id = :catalogId")
    suspend fun findCatalogById(catalogId : String) : CardItem?

}