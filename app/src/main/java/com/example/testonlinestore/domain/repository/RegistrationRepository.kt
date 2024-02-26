package com.example.testonlinestore.domain.repository

import com.example.testonlinestore.domain.model.registration.UserAccount
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {

   suspend fun createUser(userAccount : UserAccount)

}