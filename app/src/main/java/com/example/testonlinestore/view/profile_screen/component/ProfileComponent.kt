package com.example.testonlinestore.view.profile_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.registration.UserAccount
import com.example.testonlinestore.presentation.navigation.Screen
import com.example.testonlinestore.view.profile_screen.ProfileViewModel

@Composable
fun TopBarText(modifier : Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center
        ) {
        Text(
            text = stringResource(R.string.personal_office),
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Composable
fun UserData(
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
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = surname,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = number.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(R.color.light_grey_component)
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

@Composable
fun FavoriteCard(
    count : Int,
    modifier : Modifier = Modifier,
    navController : NavController
) {

    val nameFavorite = when {
        count == 0 -> "товаров"
        count == 1 -> "товар"
        count < 4 -> "товара"
        else -> "товаров"
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

@Composable
fun OtherCard(
    drawable : Int,
    text : Int,
    color : Int = R.color.black,
    contentDescription : Int,
    modifier : Modifier = Modifier
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

@Composable
fun ContentProfile(
    modifier : Modifier = Modifier,
    viewModel : ProfileViewModel = hiltViewModel(),
    navController : NavController
    ) {
    val accountState by viewModel.account.collectAsState(initial = null)

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(5.dp)
            .verticalScroll(scrollState),
    ) {
        accountState?.let {
            UserData(
                it
            )
        }
        FavoriteCard(
            count = viewModel.count.value,
            navController = navController
            )
        OtherCard(
            drawable = R.drawable.shop,
            text = R.string.shop,
            contentDescription = R.string.shop,
            color = R.color.pink,
            modifier = Modifier.padding(top = 8.dp)
        )
        OtherCard(
            drawable = R.drawable.feedback,
            text = R.string.feedback,
            contentDescription = R.string.feedback,
            color = R.color.orange,
            modifier = Modifier.padding(top = 8.dp)
        )
        OtherCard(
            drawable = R.drawable.offer_icon,
            text = R.string.offer,
            contentDescription = R.string.offer,
            color = R.color.grey,
            modifier = Modifier.padding(top = 8.dp)
        )
        OtherCard(
            drawable = R.drawable.action_back,
            text = R.string.return_item,
            contentDescription = R.string.return_item,
            color = R.color.grey,
            modifier = Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = modifier.padding(top = 10.dp))

        ButtonExit(
            onCreateUserClick = {
                navController.navigate(
                    route = Screen.RegistrationScreen.route
                )
                viewModel.removeUser()

            }
        )

    }


}

@Preview
@Composable
fun ProfileScreen_prev() {

}