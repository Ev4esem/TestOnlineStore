package com.example.testonlinestore.view.profile_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testonlinestore.R
import com.example.testonlinestore.presentation.bottom_navigation_bar.BottomNavigationBar
import com.example.testonlinestore.view.bases.TopBarText
import com.example.testonlinestore.view.profile_screen.component.ContentProfile

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController : NavController,
    onEvent : (ProfileEvent) -> Unit,
    uiState : ProfileUiState
) {
    Box {
        Scaffold(
            topBar = { TopBarText(
                text = R.string.personal_office,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(20.dp)
            ) },
            content = {
                ContentProfile(
                    modifier = Modifier.padding(it),
                    navController = navController,
                    onEvent = onEvent,
                    uiState = uiState
                )
            },
        )
    }
}



