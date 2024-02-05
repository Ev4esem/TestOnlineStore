package com.example.testonlinestore.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testonlinestore.view.catalog_screen.CatalogScreen
import com.example.testonlinestore.view.profile_screen.ProfileScreen
import com.example.testonlinestore.view.registration_screen.LogInScreen
import com.example.testonlinestore.view.registration_screen.RegistrationViewModel

@Composable
fun Navigation(
    navController : NavHostController,
    padding : PaddingValues,
    viewModel : RegistrationViewModel = hiltViewModel(),
    context : Context
) {

    NavHost(
        navController = navController,
        startDestination = Screen.RegistrationScreen.route,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable(route = Screen.RegistrationScreen.route) {
                viewModel.isBottomNavBarVisible = false
                LogInScreen(navController = navController,context = context)
            }
            composable(route = Screen.MainScreen.route) {
                //MainScreen()
            }

            composable(route = Screen.CatalogScreen.route) {
                CatalogScreen(navController = navController)
            }

            composable(route = Screen.CardScreen.route) {
                //CardScreen()
            }

            composable(route = Screen.DiscountScreen.route) {
                //DiscountScreen()
            }

            composable(
                route = Screen.ProfileScreen.route
            ) {
                viewModel.isBottomNavBarVisible = true
                ProfileScreen(navController = navController)
            }

        }
    )
}

