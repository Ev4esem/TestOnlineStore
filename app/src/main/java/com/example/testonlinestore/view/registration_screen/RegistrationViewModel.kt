package com.example.testonlinestore.view.registration_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.domain.use_case.registration.CreateUserUseCase
import com.example.testonlinestore.utils.DataStoreManager
import com.example.testonlinestore.utils.EffectHandler
import com.example.testonlinestore.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val createUserUseCase : CreateUserUseCase,
    private val dataStoreManager : DataStoreManager
) : ViewModel(),EffectHandler<RegistrationEffect>,EventHandler<RegistrationEvent> {


    fun createUser(userAccount : UserAccount) = viewModelScope.launch {
        createUserUseCase(userAccount)

     }

    fun saveUserId(userId : String) {
        viewModelScope.launch {
            dataStoreManager.saveUserId(userId)
        }
    }

    override val effectChannel : Channel<RegistrationEffect> = Channel()
    override fun obtainEvent(event : RegistrationEvent) {
        when(event) {
            is RegistrationEvent.LoginSuccess -> {
                createUser(event.userAccount)
            }
            is RegistrationEvent.SaveUserId -> {
                saveUserId(event.userId)
            }
        }
    }
}


sealed interface RegistrationEffect {
    data class ShowToast(
        val message : String
    ) : RegistrationEffect
}
/**
 * Обработка событий для регистрации
 * Если регистрация прошла успешно вызывается toast ,в
 * случае не корректных данный выходит тост с ошибкой
 */
sealed interface RegistrationEvent {

    data class LoginSuccess(
        val userAccount : UserAccount
    ) : RegistrationEvent

    data class SaveUserId(
        val userId : String
    ) : RegistrationEvent

}


