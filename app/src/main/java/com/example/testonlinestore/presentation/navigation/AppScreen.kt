package com.example.testonlinestore.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(bottom = it.calculateBottomPadding()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CatalogNavHost(
                    modifier = modifier.fillMaxHeight(),
                    navController = appState.navController
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = appState.navController)

        }
    )

}