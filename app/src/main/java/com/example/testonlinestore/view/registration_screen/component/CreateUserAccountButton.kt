package com.example.testonlinestore.view.registration_screen.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R


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