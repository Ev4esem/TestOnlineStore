package com.example.testonlinestore.data.repositories

import com.example.testonlinestore.data.database.dao.AccountDao
import com.example.testonlinestore.di.IoDispatcher
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.domain.repository.RegistrationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RegistrationRepositoryImpl @Inject constructor (
    val accountDao : AccountDao,
    ) : RegistrationRepository {

    override suspend fun createUser(userAccount : UserAccount)   {
        val account = accountDao.createUser(userAccount)
        return account
    }



}