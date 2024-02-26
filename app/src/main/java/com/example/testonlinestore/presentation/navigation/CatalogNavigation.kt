package com.example.testonlinestore.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.testonlinestore.presentation.navigation.route.CatalogRoute
import com.example.testonlinestore.presentation.navigation.route.FavoriteRoute
import com.example.testonlinestore.presentation.navigation.route.LogInRoute
import com.example.testonlinestore.presentation.navigation.route.ProfileRoute

fun NavGraphBuilder.screens(
    navController : NavController
) {

    composable(
        route = Screen.RegistrationScreen.route
    ) {
        LogInRoute(navController)
    }

    composable(route = Screen.ProfileScreen.route) {
        ProfileRoute(navController)
    }

    composable(route = Screen.FavoriteScreen.route) {
        FavoriteRoute(navController)
    }

    composable(route = Screen.CatalogScreen.route) {
        CatalogRoute(navController = navController)
    }

}
