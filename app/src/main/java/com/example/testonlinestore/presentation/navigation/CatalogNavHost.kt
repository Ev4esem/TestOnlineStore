package com.example.testonlinestore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun CatalogNavHost(
    modifier : Modifier = Modifier,
    navController : NavHostController
) {

    val startDestination = MAIN_ROUTE_PATTERN

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        main {
            screens(navController)
        }

    }

}