package com.example.testonlinestore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberCatalogAppState(
    navController : NavHostController = rememberNavController()
) : CatalogAppState {

    return remember(navController) {
        CatalogAppState(
            navController = navController
        )
    }

}

@Stable
class CatalogAppState(
    val navController : NavHostController
) {
    val currentDestination : NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun onBackClick() {
        navController.popBackStack()
    }

}