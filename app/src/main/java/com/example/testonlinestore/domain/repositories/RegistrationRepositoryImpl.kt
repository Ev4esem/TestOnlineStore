package com.example.testonlinestore.domain.repositories

import com.example.testonlinestore.domain.database.AccountDao
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.repository.RegistrationRepository
import javax.inject.Inject


class RegistrationRepositoryImpl @Inject constructor (val accountDao : AccountDao) : RegistrationRepository {

    override suspend fun createUser(userAccount : UserAccount) {

        accountDao.createUser(userAccount)

    }

}