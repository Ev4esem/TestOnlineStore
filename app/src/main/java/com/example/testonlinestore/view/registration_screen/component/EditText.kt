package com.example.testonlinestore.view.registration_screen.component

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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R


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
    name : String,
    onNameChange : (String) -> Unit
) {


    TextField(
        value = name,
        onValueChange = { userInput ->
            onNameChange(userInput)
        },
        trailingIcon = {

            if (name.isNotEmpty()) {
                IconButton(
                    onClick = { onNameChange("") }
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
        isError = name.isEmpty(),
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
    surname : String,
    onSurnameChange : (String) -> Unit
) {


    TextField(
        value = surname,
        onValueChange = { userInput ->
            onSurnameChange(userInput)
        },
        trailingIcon = {


            if (surname.isNotEmpty()) {
                IconButton(
                    onClick = { onSurnameChange("") }
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
    number : String,
    onNumberChange : (String) -> Unit
) {


    TextField(
        value = number,
        onValueChange = { userInput ->
            if (userInput.length <= 11) {
                onNumberChange(userInput)
            }
        },
        trailingIcon = {


            if (number.isNotEmpty()) {
                IconButton(
                    onClick = { onNumberChange("") }
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
        isError = number.length != 11,
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
    name : String,
    surname : String,
    number : String,
    onCreateUserClick : () -> Unit,
) {

    val isButtonEnabled = remember(name, surname, number) {
        name.isNotEmpty() && surname.isNotEmpty() && number.length == 11 && number.isNotEmpty()
    }

    Button(

        onClick = {
            onCreateUserClick()

        },
        enabled = isButtonEnabled,
        modifier = modifier
            .width(343.dp)
            .height(51.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.pink),
            disabledContainerColor = colorResource(R.color.light_pink)

        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = stringResource(R.string.come),
            color = colorResource(R.color.white)
            )
    }

}

@Composable
fun ContentRegistration() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputName(name = "", onNameChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        InputSurname(surname = "", onSurnameChange = { })
        Spacer(modifier = Modifier.height(16.dp))
        InputNumber(number = "", onNumberChange = {})
        Spacer(modifier = Modifier.height(32.dp))
        CreateUserAccountButton(name = "", number = "", surname = "", onCreateUserClick = { })
    }

}

@Preview
@Composable
fun EditTextPrev() {
    ContentRegistration()

}