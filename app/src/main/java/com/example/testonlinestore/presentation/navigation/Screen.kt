package com.example.testonlinestore.presentation.navigation

import androidx.compose.ui.res.stringResource
import com.example.testonlinestore.R

sealed class Screen(
    val route : String

) {

    object RegistrationScreen : Screen("registration_screen")
    object ProfileScreen : Screen("profile_screen")
    object MainScreen : Screen("profile_screen")
    object CatalogScreen : Screen("profile_screen")
    object DiscountScreen : Screen("profile_screen")
    object CardScreen : Screen("profile_screen")
}
