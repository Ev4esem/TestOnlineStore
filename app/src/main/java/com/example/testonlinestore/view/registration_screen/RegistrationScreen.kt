package com.example.testonlinestore.view.registration_screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.navigation.Screen
import com.example.testonlinestore.utils.saveUserId
import com.example.testonlinestore.view.profile_screen.ProfileViewModel
import com.example.testonlinestore.view.registration_screen.component.BottomBarText
import com.example.testonlinestore.view.registration_screen.component.CreateUserAccountButton
import com.example.testonlinestore.view.registration_screen.component.InputName
import com.example.testonlinestore.view.registration_screen.component.InputNumber
import com.example.testonlinestore.view.registration_screen.component.InputSurname
import com.example.testonlinestore.view.registration_screen.component.TopBarText

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    viewModel : RegistrationViewModel = hiltViewModel(),
    context : Context,
    navController : NavController
) {

    var name by remember {
        mutableStateOf("")
    }
    var surname by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")
    }

    Box {

        Scaffold(
            topBar = { TopBarText() },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputName(
                        name = name,
                        onNameChange = { newName -> name = newName })
                    Spacer(modifier = Modifier.height(16.dp))
                    InputSurname(
                        surname = surname,
                        onSurnameChange = { newSurname -> surname = newSurname })
                    Spacer(modifier = Modifier.height(16.dp))
                    InputNumber(
                        number = number,
                        onNumberChange = { newNumber -> number = newNumber })
                    Spacer(modifier = Modifier.height(32.dp))
                    CreateUserAccountButton(
                        name = name,
                        surname = surname,
                        number = number,
                        onCreateUserClick = {
                            viewModel.createUser(
                                UserAccount(name = name, surname = surname, number = number.toLong())
                            )
                            saveUserId(context,number)
                            navController.navigate(
                                route = Screen.ProfileScreen.route
                            )

                        }
                    )
                }

            },
            bottomBar = { BottomBarText() }
        )
    }
}