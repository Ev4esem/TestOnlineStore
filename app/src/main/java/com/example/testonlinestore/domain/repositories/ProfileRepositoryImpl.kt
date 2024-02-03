package com.example.testonlinestore.domain.repositories

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import com.example.testonlinestore.domain.database.AccountDao
import com.example.testonlinestore.domain.database.CardDao
import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val accountDao : AccountDao,
    private val cardDao : CardDao
) : ProfileRepository {

    override fun getItemCountFlow() : Flow<Int> {
        return cardDao.getAllCardItemsFlow()
            .map { cardList ->
                calculateItemCount(cardList)
            }
    }

    private fun calculateItemCount(cardList : List<CardItem>) : Int {
        return cardList.size
    }

    override suspend fun removeAccount(user : UserAccount) {
        accountDao.removeUser(user)
    }

    override fun getDataAccount(number : Long) : Flow<UserAccount?> {
        return accountDao.checkNumber(number)
    }


}