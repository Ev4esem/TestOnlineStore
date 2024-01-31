package com.example.testonlinestore.view.registration_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.use_case.registration.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegistrationViewState(
    var name : String = "",
    var surname : String = "",
    var number : String = ""
)

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val createUserUseCase : CreateUserUseCase
) : ViewModel() {

    private val _registrationViewState : MutableLiveData<RegistrationViewState> = MutableLiveData(
        RegistrationViewState()
    )
    private val _surname : MutableLiveData<String> = MutableLiveData("")
    private val _number : MutableLiveData<String> = MutableLiveData("")

    val registrationViewState : LiveData<RegistrationViewState> = _registrationViewState
    val surname : LiveData<String> = _surname
    val number : LiveData<String> = _number

    fun clearInput(fieldName : String,text : String) {

        val currentViewState = _registrationViewState.value ?: RegistrationViewState()
        val updatedViewState = when(fieldName) {
            "name" -> currentViewState.copy(name = text)
            "surname" -> currentViewState.copy(surname = text)
            "number" -> currentViewState.copy(number = text)
            else -> throw IllegalArgumentException("Invalid field name")
        }
        _registrationViewState.value = updatedViewState

    }

    suspend fun createUser(userAccount : UserAccount) = viewModelScope.launch {
        createUserUseCase(userAccount)
    }

}