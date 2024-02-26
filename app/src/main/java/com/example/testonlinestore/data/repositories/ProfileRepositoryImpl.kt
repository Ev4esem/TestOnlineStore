package com.example.testonlinestore.data.repositories

import com.example.testonlinestore.data.database.dao.AccountDao
import com.example.testonlinestore.data.database.dao.CardDao
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val accountDao : AccountDao,
    private val cardDao : CardDao,
) : ProfileRepository {

    override suspend fun getItemCountFlow() : Int {
        return cardDao.getAllCardItems().size
    }

    override suspend fun removeAccount(user : Long) : Int  {
       return accountDao.removeUser(user)
    }

    override fun getDataAccount(number : Long) : Flow<UserAccount?> {
        return accountDao.checkNumber(number)
    }


}