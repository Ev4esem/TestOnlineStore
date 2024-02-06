package com.example.testonlinestore.view.favorite_screen

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.testonlinestore.view.favorite_screen.component.FavoriteContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen() {

    Scaffold(
        topBar = {},
        content = { FavoriteContent() },
        bottomBar = {}
    )

}