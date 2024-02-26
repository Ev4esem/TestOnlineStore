package com.example.testonlinestore.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.testonlinestore.presentation.bottom_navigation_bar.BottomNavigationBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppScreen(
    modifier : Modifier = Modifier,
    appState : CatalogAppState = rememberCatalogAppState()
) {

    Scaffold(
        modifier = modifier
            .navigationBarsPadding(),
        bottomBar = {
            BottomNavigationBar(navController = appState.navController)
        }
    ) {
        CatalogNavHost(
            modifier = modifier,
            navController = appState.navController
        )
    }

}