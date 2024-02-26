package com.example.testonlinestore.view.details_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R

@Composable
fun TopBarDetails(
    onClickBack: () -> Unit,

) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 8.dp, top = 14.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // todo Add resource
        IconButton(

            onClick = onClickBack

            ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "")
        }

        Icon(
            painter = painterResource(R.drawable.shape_icon),
            contentDescription = "")

    }

}
