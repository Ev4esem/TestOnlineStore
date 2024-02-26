package com.example.testonlinestore.domain.repository

import com.example.testonlinestore.domain.model.registration.UserAccount
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getItemCountFlow() : Int

    suspend fun removeAccount(user : Long) : Int

    fun getDataAccount(number : Long) : Flow<UserAccount?>

}