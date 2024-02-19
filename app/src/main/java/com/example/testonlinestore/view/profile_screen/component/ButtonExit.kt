package com.example.testonlinestore.view.profile_screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R


@Composable
fun ButtonExit(
    onCreateUserClick : () -> Unit,
) {

    Button(

        colors = ButtonDefaults.buttonColors(colorResource(R.color.light_grey_background)),
        onClick = {
            onCreateUserClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(51.dp)
        ,
        shape = MaterialTheme.shapes.medium
    ) {

        Text(
            text = stringResource(R.string.exit),
            color = colorResource(R.color.black)
        )

    }

}