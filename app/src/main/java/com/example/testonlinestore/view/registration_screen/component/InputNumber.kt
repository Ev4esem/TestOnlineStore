package com.example.testonlinestore.view.registration_screen.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R

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
        // todo Add in resource
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