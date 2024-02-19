package com.example.testonlinestore.view.registration_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R

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
