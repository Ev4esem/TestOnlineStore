package com.example.testonlinestore.view.registration_screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testonlinestore.R
import com.example.testonlinestore.view.registration_screen.RegistrationViewModel
import com.example.testonlinestore.view.registration_screen.RegistrationViewState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LogInScreen() {

    Scaffold(
        topBar = { TopBarText() },
        content = { EditTextPrev() },
        bottomBar = { BottomBarText() }
    )


}

@Composable
fun TopBarText(modifier : Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.input),
            style = MaterialTheme.typography.titleMedium
        )
    }

}

@Composable
fun BottomBarText(modifier : Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 25.dp)
    ) {
        Text(
            stringResource(R.string.description_click_input1),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.grey)

        )
        Text(
            stringResource(R.string.description_click_input2),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.grey)

        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputName(
    modifier : Modifier = Modifier,
    viewModel : RegistrationViewModel = hiltViewModel()
) {

    val registrationViewState by viewModel.registrationViewState.observeAsState()

    TextField(
        value = registrationViewState?.name ?: "",
        onValueChange = { userInput ->
            viewModel.clearInput("name",userInput)
        },
        trailingIcon = {


            if (registrationViewState?.name?.isNotEmpty() == true) {
                IconButton(
                    onClick = { viewModel.clearInput("name","") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.clear_input)
                    )
                }
            }



        },
        label = {
                Text("Имя")
        },
        isError = registrationViewState?.name.isNullOrEmpty(),
        modifier = modifier
            .width(343.dp)
            .height(51.dp)
            .clip(RoundedCornerShape(10.dp)),

        maxLines = 1

    )
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSurname(
    modifier : Modifier = Modifier,
) {

    var surname by remember {
        mutableStateOf("")
    }


    TextField(
        value = surname,
        onValueChange = { userInput ->
            surname = userInput
        },
        trailingIcon = {


            if (surname.isNotEmpty()) {
                IconButton(
                    onClick = { surname = "" }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.clear_input)
                    )
                }
            }



        },
        label = {
            Text("Фамилие")
        },
        isError = surname.isEmpty(),
        modifier = modifier
            .width(343.dp)
            .height(51.dp)
            .clip(RoundedCornerShape(10.dp)),
        maxLines = 1
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputNumber(
    modifier : Modifier = Modifier,
    viewModel : RegistrationViewModel = hiltViewModel()
) {

    var number by remember {
        mutableStateOf("")
    }


    TextField(
        value = number,
        onValueChange = { userInput ->
            if (userInput.length <= 11) {
                number = userInput
            }
            if (number.isEmpty()) {

            }
        },
        trailingIcon = {


            if (number.isNotEmpty()) {
                IconButton(
                    onClick = { number = "" }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.clear_input)
                    )
                }
            }



        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = {
            Text("Номер телефона")
        },
        isError = number.length != 11 ,
        modifier = modifier
            .width(343.dp)
            .height(51.dp)
            .clip(RoundedCornerShape(10.dp)),
        maxLines = 1

    )

}

@Composable
fun CreateUserAccountButton(
    modifier : Modifier = Modifier,
    viewModel : RegistrationViewModel = hiltViewModel()
) {

    Button(

        onClick = {

        },
        modifier = modifier
            .width(343.dp)
            .height(51.dp)
            .clip(RoundedCornerShape(5.dp)),
        shape = ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(colorResource(R.color.pink))
    ) {
        Text(text = stringResource(R.string.come))
    }

}

@Preview
@Composable
fun EditTextPrev() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputName()
        Spacer(modifier = Modifier.height(16.dp))
        InputSurname()
        Spacer(modifier = Modifier.height(16.dp))
        InputNumber()
        Spacer(modifier = Modifier.height(32.dp))
        CreateUserAccountButton()
    }

}