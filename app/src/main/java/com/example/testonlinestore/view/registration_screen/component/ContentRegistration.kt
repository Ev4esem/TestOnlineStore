package com.example.testonlinestore.view.registration_screen.component

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.navigation.Screen
import com.example.testonlinestore.utils.saveUserId
import com.example.testonlinestore.view.bases.InputEditText
import com.example.testonlinestore.view.registration_screen.RegistrationViewModel


@Composable
fun ContentRegistration(
    viewModel : RegistrationViewModel,
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputEditText(
            title = name,
            onTitleChange = { newName -> name = newName },
            label = R.string.name
        )
        Spacer(modifier = Modifier.height(16.dp))
        InputEditText(
            title = surname,
            onTitleChange = { newSurname -> surname = newSurname },
            label = R.string.surname
        )
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

}

@Preview
@Composable
fun EditTextPrev() {

}