package com.example.testonlinestore.view.profile_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testonlinestore.R
import com.example.testonlinestore.presentation.navigation.Screen


@Composable
fun FavoriteCard(
    count : Int,
    modifier : Modifier = Modifier,
    navController : NavController
) {

    val nameFavorite = when {
        count == 0 -> stringResource(id = R.string.itemov)
        count == 1 -> stringResource(id = R.string.item)
        count < 4 -> stringResource(id = R.string.itema)
        else -> stringResource(id = R.string.itemov)
    }
    val nameState by remember {
        mutableStateOf(nameFavorite)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .padding(top = 24.dp)
            .background(color = colorResource(R.color.light_grey_background))
            .clickable {
                navController.navigate(Screen.FavoriteScreen.route)
            }
        ,

        ) {
        Row(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(
                painter = painterResource(R.drawable.heart_icon),
                contentDescription = stringResource(R.string.account),
                modifier = modifier.padding(10.dp)
            )


            Column(
                modifier = modifier.weight(1f)
            ) {

                Text(
                    text = stringResource(R.string.favorite),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = modifier.height(5.dp))
                Text(
                    text = "$count $nameState",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(R.color.light_grey_component)
                )
            }

            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = stringResource(R.string.come),
                modifier = modifier.padding(10.dp)
            )
        }
    }




}