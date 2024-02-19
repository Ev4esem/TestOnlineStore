package com.example.testonlinestore.view.bases

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R


@Composable
fun OtherCard(
    drawable : Int,
    text : Int,
    color : Int = R.color.black,
    contentDescription : Int,
    @SuppressLint("ModifierParameter") modifier : Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        color = colorResource(R.color.light_grey_background)
    ) {
        Row(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(
                painter = painterResource(drawable),
                contentDescription = stringResource(contentDescription),
                modifier = Modifier.padding(10.dp),
                tint = colorResource(id = color)
            )


            Column(
                modifier = modifier.weight(1f)
            ) {

                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.come),
                modifier = Modifier.padding(10.dp)
            )
        }
    }

}