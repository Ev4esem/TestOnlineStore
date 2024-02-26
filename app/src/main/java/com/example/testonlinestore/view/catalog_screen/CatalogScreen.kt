package com.example.testonlinestore.view.catalog_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.view.catalog_screen.component.CatalogScreenContent
import com.example.testonlinestore.view.catalog_screen.component.TopBarText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CatalogScreen(
    uiState : CatalogUiState,
    onEvent : (CatalogEvent) -> Unit,
    navController : NavController
) {
    Scaffold(
        topBar = { TopBarText() },
        content = {
           CatalogScreenContent(
               uiState = uiState,
               onEvent = onEvent,
               navController
           )
        },
        bottomBar = {}
    )



}