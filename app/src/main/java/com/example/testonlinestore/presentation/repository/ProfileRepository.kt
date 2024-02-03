package com.example.testonlinestore.presentation.repository

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import com.example.testonlinestore.domain.model.registration.UserAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ProfileRepository {

    fun getItemCountFlow() : Flow<Int>

    suspend fun removeAccount(user : UserAccount)

    fun getDataAccount(number : Long) : Flow<UserAccount?>

}