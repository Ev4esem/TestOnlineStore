package com.example.testonlinestore.view.profile_screen

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.use_case.profile.GetUserAccountUseCase
import com.example.testonlinestore.presentation.use_case.profile.ItemCountUseCase
import com.example.testonlinestore.presentation.use_case.profile.RemoveAccountUseCase
import com.example.testonlinestore.utils.SharedPref
import com.example.testonlinestore.utils.SharedPref.DEFAULT_VALUE_USER_ID
import com.example.testonlinestore.utils.removeUserId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (
    application : Application,
    private val getUserAccountUseCase : GetUserAccountUseCase,
    private val removeAccountUseCase : RemoveAccountUseCase,
    private val itemCountUseCase : ItemCountUseCase
) : ViewModel() {

    private val context = application
    private var _count = MutableStateFlow(0)
    val count : StateFlow<Int> = _count
    val account = getUserAccountUseCase(getNumber().toLong())


    init {
        viewModelScope.launch {
            itemCountUseCase.getItemCountFlow().collect {
                _count.value = it
            }
        }
    }

    private fun getNumber() : String {

        val sharedPref = context.getSharedPreferences(
            SharedPref.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE
        )
        return sharedPref.getString(SharedPref.USER_ID_KEY, DEFAULT_VALUE_USER_ID)
            ?: DEFAULT_VALUE_USER_ID

    }

     fun removeUser() = viewModelScope.launch(Dispatchers.IO) {
         account.collect { userAccount ->
             userAccount?.let {
                 removeAccountUseCase(it)
             }
         }
    }






}