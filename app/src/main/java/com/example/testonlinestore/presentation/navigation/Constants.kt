package com.example.testonlinestore.presentation.navigation

import com.example.testonlinestore.R

const val USER_ID = "user_id"
const val NAME_USER = "name"
const val SURNAME_USER = "surname"
const val NUMBER_USER = "number"

object Constants {

    val BottomNavItems = listOf(
        BottomNavItem(
            label = R.string.home,
            icon = R.drawable.main_screen,
            route = Screen.MainScreen.route
        ),
        BottomNavItem(
            label = R.string.catalog,
            icon = R.drawable.catalog_screen,
            route = Screen.CatalogScreen.route
        ),
        BottomNavItem(
            label = R.string.card,
            icon = R.drawable.card_screen,
            route = Screen.CardScreen.route
        ),
        BottomNavItem(
            label = R.string.discount,
            icon = R.drawable.discount_screen,
            route = Screen.DiscountScreen.route
        ),
        BottomNavItem(
            label = R.string.account,
            icon = R.drawable.profile_screen_bottom_bar,
            route = Screen.ProfileScreen.route
        )
    )

}