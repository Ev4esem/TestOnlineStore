package com.example.testonlinestore.presentation.navigation

sealed class Screen(
    val route : String

) {

    object RegistrationScreen : Screen("registration_screen")
    object ProfileScreen : Screen("profile_screen")
    object MainScreen : Screen("main_screen")
    object CatalogScreen : Screen("catalog_screen")
    object DiscountScreen : Screen("discount_screen")
    object CardScreen : Screen("card_screen")
    object DetailsScreen : Screen("details_screen")
}
