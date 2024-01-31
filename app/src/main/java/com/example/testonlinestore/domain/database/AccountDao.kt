package com.example.testonlinestore.domain.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.testonlinestore.domain.model.registration.UserAccount

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(user : UserAccount)


    @Delete
    suspend fun removeUser(user : UserAccount)



}