package com.example.testonlinestore.view.profile_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.data.database.models.CardItem
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.domain.use_case.profile.GetUserAccountUseCase
import com.example.testonlinestore.domain.use_case.profile.ItemCountUseCase
import com.example.testonlinestore.domain.use_case.profile.RemoveAccountUseCase
import com.example.testonlinestore.utils.DataStoreManager
import com.example.testonlinestore.utils.EffectHandler
import com.example.testonlinestore.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Здесь мы читаем данные из [DataStoreManager] и
 * прокидываем их в [GetUserAccountUseCase] далее в [ProfileUiState]
 * у нас есть поле [accountData] и мы наш [getUserAccountUseCase]
 * прокидываем в это поле , и уже в [ProfileScreen] мы используем
 * наш [ProfileUiState] для прокидывания [UserAccount]
 *
 *
 * Id мы получаем из [RegistrationScreen] там мы записываем данные,
 * а здесь читаем и перезаписываем
 */

@HiltViewModel
class ProfileViewModel @Inject constructor (
    private val getUserAccountUseCase : GetUserAccountUseCase,
    private val removeAccountUseCase : RemoveAccountUseCase,
    private val itemCountUseCase : ItemCountUseCase,
    private val dataStore : DataStoreManager
) : ViewModel(),EffectHandler<ProfileEffect>,EventHandler<ProfileEvent> {


    private var _productsUiState = MutableStateFlow(ProfileUiState())
    val productsUiState  = _productsUiState.asStateFlow()


    private var _count = MutableStateFlow(0)
    val count : StateFlow<Int> = _count


    override val effectChannel : Channel<ProfileEffect> = Channel()

    init {
        countFavoriteItems()
        getNumber()
    }

    override fun obtainEvent(event : ProfileEvent) {
        when(event) {
            is ProfileEvent.DeleteProfile -> {
                removeUser()
            }
        }
    }
    private fun countFavoriteItems() {
        viewModelScope.launch {
            _productsUiState.update {  currentState ->
                val countList = itemCountUseCase()
                currentState.copy(
                    countFavoriteList = countList
                )
            }
        }
    }


    private fun getNumber() {

        viewModelScope.launch {
            dataStore.userIdFlow.collect { userId ->
                if (userId.isNotEmpty()) {
                    val userAccountFlow = getUserAccountUseCase(userId)

                    _productsUiState.update { currentState ->
                        currentState.copy(
                            accountData = userAccountFlow.firstOrNull()
                        )
                    }
                    Log.d("USER_ID", "${ userAccountFlow.first() }")

                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun removeUser() {

     viewModelScope.launch {

         val userId = dataStore.userIdFlow.first()
             if (userId.isNotEmpty()) {

                 val userAccount = removeAccountUseCase(userId)
                 _productsUiState.update {  currentState ->
                     currentState.copy(
                         userId = userAccount
                     )
                 }
             }
         dataStore.saveUserId("")
        }
    }

}

data class ProfileUiState(
    val accountData : UserAccount? = null,
    val countFavoriteList : Int = 0,
    val userId : Int = 0
)

sealed interface ProfileEffect {
    data class ShowToast(
        val message : String
    ) : ProfileEffect
}

sealed interface ProfileEvent {

    object DeleteProfile : ProfileEvent

}
