package com.example.testonlinestore.view.profile_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.testonlinestore.view.profile_screen.component.ContentProfile
import com.example.testonlinestore.view.profile_screen.component.TopBarText

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController : NavController,
) {
    Box {
        Scaffold(
            topBar = { TopBarText() },
            content = {
                ContentProfile(
                    modifier = Modifier.padding(it),
                    navController = navController
                )
            },
            bottomBar = {  }
        )
    }
}



