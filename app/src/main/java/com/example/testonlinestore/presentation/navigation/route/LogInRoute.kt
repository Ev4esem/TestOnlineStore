package com.example.testonlinestore.presentation.navigation.route

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.utils.ObserveEffect
import com.example.testonlinestore.view.registration_screen.LogInScreen
import com.example.testonlinestore.view.registration_screen.RegistrationEffect
import com.example.testonlinestore.view.registration_screen.RegistrationViewModel

@Composable
fun LogInRoute(
    navController : NavController
) {

    val context = LocalContext.current
    val registrationViewModel : RegistrationViewModel = hiltViewModel()

    ObserveEffect(registrationViewModel.effectFlow) { effect ->
        when(effect) {
            is RegistrationEffect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    LogInScreen(
        onEvent = registrationViewModel::obtainEvent,
        navController = navController)


}