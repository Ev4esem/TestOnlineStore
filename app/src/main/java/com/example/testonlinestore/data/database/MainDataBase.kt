package com.example.testonlinestore.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testonlinestore.data.database.converter.FeedbackConverters
import com.example.testonlinestore.data.database.converter.PriceConverter
import com.example.testonlinestore.data.database.converter.TagsConverter
import com.example.testonlinestore.data.database.dao.AccountDao
import com.example.testonlinestore.data.database.dao.CardDao
import com.example.testonlinestore.data.database.models.CardItem
import com.example.testonlinestore.domain.model.registration.UserAccount

@Database(
    entities = [UserAccount::class, CardItem::class],
    version = 1)
@TypeConverters(FeedbackConverters::class, PriceConverter::class,TagsConverter::class)
abstract class MainDataBase : RoomDatabase() {

    abstract fun accountDao() : AccountDao

    abstract fun cardDao() : CardDao

}