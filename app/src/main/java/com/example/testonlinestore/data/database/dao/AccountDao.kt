package com.example.testonlinestore.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testonlinestore.domain.model.registration.UserAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(user : UserAccount)


    @Query("DELETE FROM user_account WHERE number LIKE '%' || :number || '%'")
    suspend fun removeUser(number : Long) : Int


    @Query("SELECT * FROM user_account WHERE number LIKE '%' || :number || '%'")
     fun checkNumber(number : Long) : Flow<UserAccount?>



}