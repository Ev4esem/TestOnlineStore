package com.example.testonlinestore.presentation.repository

import com.example.testonlinestore.domain.model.registration.UserAccount

interface RegistrationRepository {

   suspend fun createUser(userAccount : UserAccount)

}