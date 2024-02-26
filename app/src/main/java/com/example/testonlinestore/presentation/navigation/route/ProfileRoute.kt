package com.example.testonlinestore.presentation.navigation.route

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.testonlinestore.utils.ObserveEffect
import com.example.testonlinestore.view.favorite_screen.FavoriteEffect
import com.example.testonlinestore.view.favorite_screen.FavoriteEvent
import com.example.testonlinestore.view.profile_screen.ProfileEffect
import com.example.testonlinestore.view.profile_screen.ProfileScreen
import com.example.testonlinestore.view.profile_screen.ProfileViewModel

@Composable
fun ProfileRoute(
    navController : NavController
) {

    val context = LocalContext.current
    val viewModel : ProfileViewModel = hiltViewModel()
    val uiState by viewModel.productsUiState.collectAsStateWithLifecycle()

    ObserveEffect(viewModel.effectFlow) { effect ->

        when(effect) {
            is ProfileEffect.ShowToast -> {
                Toast.makeText(context,effect.message,Toast.LENGTH_LONG).show()
            }
        }
    }

    ProfileScreen(
        navController = navController,
        onEvent = viewModel::obtainEvent,
        uiState = uiState)

}