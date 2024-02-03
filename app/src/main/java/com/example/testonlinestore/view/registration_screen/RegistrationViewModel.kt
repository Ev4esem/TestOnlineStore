package com.example.testonlinestore.view.registration_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.use_case.registration.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val createUserUseCase : CreateUserUseCase
) : ViewModel() {

    var isBottomNavBarVisible by mutableStateOf(true)

     fun createUser(userAccount : UserAccount) = viewModelScope.launch {
        createUserUseCase(userAccount)
    }

}