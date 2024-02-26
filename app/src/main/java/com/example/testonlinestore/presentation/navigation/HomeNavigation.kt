package com.example.testonlinestore.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation

const val MAIN_ROUTE_PATTERN = "main_graph"


fun NavController.navigateToProfile(navOptions : NavOptions? = null) {
    this.navigate(Screen.ProfileScreen.route, navOptions)
}

fun NavController.navigateToRegistration(navOptions : NavOptions? = null) {
    this.navigate(Screen.RegistrationScreen.route,navOptions)
}


fun NavGraphBuilder.main(
    nestedGraphs : NavGraphBuilder.() -> Unit
) {
    navigation(
        route = MAIN_ROUTE_PATTERN,
        startDestination = Screen.RegistrationScreen.route
    ) {
        nestedGraphs()
    }
}

