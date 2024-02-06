package com.example.testonlinestore.presentation.repository

import com.example.testonlinestore.domain.model.registration.UserAccount
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getItemCountFlow() : Int

    suspend fun removeAccount(user : UserAccount)

    fun getDataAccount(number : Long) : Flow<UserAccount?>

}