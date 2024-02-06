package com.example.testonlinestore.view.details_screen

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.testonlinestore.view.details_screen.component.ContentDetailsScreen
import com.example.testonlinestore.view.details_screen.component.TopBarDetails

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController : NavController,catalogId : String) {

    Scaffold(
        topBar = {
        },
        content = {
            ContentDetailsScreen(navController = navController, catalogId = catalogId)
        },
        bottomBar = {}
    )

}