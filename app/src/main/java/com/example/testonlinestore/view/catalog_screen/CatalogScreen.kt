package com.example.testonlinestore.view.catalog_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.utils.ProgressBar
import com.example.testonlinestore.utils.Resource
import com.example.testonlinestore.view.catalog_screen.component.CatalogScreenContent
import com.example.testonlinestore.view.catalog_screen.component.ListProduct
import com.example.testonlinestore.view.catalog_screen.component.TagList
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
           CatalogScreenContent(navController = navController)
        },
        bottomBar = {}
    )



}