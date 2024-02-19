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
    viewModel : CatalogViewModel = hiltViewModel(),
    navController : NavController
) {
    Scaffold(
        topBar = { TopBarText() },
        content = {
           CatalogScreenContent(navController = navController, viewModel = viewModel)
        },
        bottomBar = {}
    )



}