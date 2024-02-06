package com.example.testonlinestore.domain.repositories

import com.example.testonlinestore.domain.database.AccountDao
import com.example.testonlinestore.domain.database.CardDao
import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val accountDao : AccountDao,
    private val cardDao : CardDao
) : ProfileRepository {

    override suspend fun getItemCountFlow() : Int {
        return cardDao.getAllCardItemsFlow().size
    }


    override suspend fun removeAccount(user : UserAccount) {
        accountDao.removeUser(user)
    }

    override fun getDataAccount(number : Long) : Flow<UserAccount?> {
        return accountDao.checkNumber(number)
    }


}