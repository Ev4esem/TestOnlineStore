package com.example.testonlinestore.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testonlinestore.R
import com.example.testonlinestore.view.registration_screen.RegistrationViewModel

@Composable
fun BottomNavigationBar(
    navController : NavHostController,
    viewModel : RegistrationViewModel = hiltViewModel()
    ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    if (viewModel.isBottomNavBarVisible) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.white)),
        ) {


            Constants.BottomNavItems.forEach { navItem ->

                AddItem(
                    screen = navItem,
                    currentDestination = currentRoute,
                    navController = navController)

            }

        }
    }


}

@Composable
fun RowScope.AddItem(
    screen : BottomNavItem,
    currentDestination : NavDestination?,
    navController : NavHostController
) {

    NavigationBarItem(
        label = {
            Text(
                text = stringResource(screen.label)
            )
        },
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
}