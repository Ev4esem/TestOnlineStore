package com.example.testonlinestore.domain.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.testonlinestore.domain.model.registration.UserAccount

@Dao
interface AccountDao {

    @Insert
    fun createUser(user : UserAccount)


    @Delete
    fun removeUser(user : UserAccount)



}