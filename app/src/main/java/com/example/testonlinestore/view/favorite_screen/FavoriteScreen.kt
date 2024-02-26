package com.example.testonlinestore.view.favorite_screen

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.bumptech.glide.load.model.UnitModelLoader
import com.example.testonlinestore.view.favorite_screen.component.FavoriteContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    onClickBack : () -> Unit,
    onEvent : (FavoriteEvent) -> Unit,
    uiState : FavoriteUiState
) {

    Scaffold(
        topBar = {},
        content = { FavoriteContent(
            onClickBack = onClickBack,
            onEvent = onEvent,
            uiState = uiState
        ) },
        bottomBar = {}
    )

}