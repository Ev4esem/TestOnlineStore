package com.example.testonlinestore.view.registration_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.R
import com.example.testonlinestore.view.bases.TopBarText
import com.example.testonlinestore.view.registration_screen.component.BottomBarText
import com.example.testonlinestore.view.registration_screen.component.ContentRegistration

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    viewModel : RegistrationViewModel = hiltViewModel(),
    context : Context,
    navController : NavController
) {
    Box {

        Scaffold(
            topBar = { TopBarText(
                text = R.string.input,
                modifier = Modifier.padding(
                    top = 15.dp,
                    bottom = 15.dp
                    )
            ) },
            content = {
                ContentRegistration(
                    viewModel = viewModel,
                    context = context,
                    navController = navController)
            },
            bottomBar = { BottomBarText() }
        )
    }
}