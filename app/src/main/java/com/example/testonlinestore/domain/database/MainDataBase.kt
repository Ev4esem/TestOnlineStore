package com.example.testonlinestore.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.domain.model.registration.UserAccount

@Database(entities = [UserAccount::class,CardItem::class], version = 1)
abstract class MainDataBase : RoomDatabase() {

    abstract fun accountDao() : AccountDao

    abstract fun cardDao() : CardDao

}