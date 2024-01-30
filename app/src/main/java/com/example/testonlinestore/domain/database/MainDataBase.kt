package com.example.testonlinestore.domain.database

import androidx.room.Database
import com.example.testonlinestore.domain.model.registration.UserAccount

@Database(entities = [UserAccount::class], version = 1)
abstract class MainDataBase {

    abstract fun accountDao() : AccountDao

}