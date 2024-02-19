package com.example.testonlinestore.view.profile_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.registration.UserAccount


@Composable
fun UserDataCard(
    userData : UserAccount
) {


    val name by remember {
        mutableStateOf(userData.name)
    }

    val surname by remember {
        mutableStateOf(userData.surname)
    }

    val number by remember {
        mutableStateOf(userData.number)
    }


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        color = colorResource(R.color.light_grey_background)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Icon(
                painter = painterResource(R.drawable.profile_screen),
                contentDescription = stringResource(R.string.account),
                modifier = Modifier.padding(10.dp)
            )


            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row {
                    DataUserText(
                        title = name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    DataUserText(
                        title = surname,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                DataUserText(
                    title = number.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = R.color.light_grey_component
                )
            }

            Icon(
                painter = painterResource(R.drawable.exit_icon),
                contentDescription = stringResource(R.string.come),
                modifier = Modifier.padding(10.dp)
            )
        }
    }

}