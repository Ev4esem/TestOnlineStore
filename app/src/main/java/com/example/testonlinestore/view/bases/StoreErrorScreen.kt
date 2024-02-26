package com.example.testonlinestore.view.bases

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R


@Composable
fun StoreErrorScreen(
    modifier : Modifier = Modifier,
    errorText : String,
    onClickRetry : () -> Unit
) {

    Surface(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.primary
    ) {

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(15.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_no_connection),
                contentDescription = stringResource(id = R.string.no_connection))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorText,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                onClick = onClickRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(
                    vertical = 15.dp,
                    horizontal = 25.dp,
                )
                ) {

                Text(
                    text = stringResource(id = R.string.repeat),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )

            }

        }


    }

}

@Preview
@Composable
fun PreviewStoreErrorScreen() {

    MaterialTheme {
        StoreErrorScreen(
            errorText = "Произошла ошибка при загрузке данных, проверьте подключение к сети",
            onClickRetry = {}
        )
    }

}