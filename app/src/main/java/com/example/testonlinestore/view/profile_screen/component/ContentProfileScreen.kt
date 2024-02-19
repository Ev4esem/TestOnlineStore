package com.example.testonlinestore.view.profile_screen.component

import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.R
import com.example.testonlinestore.presentation.navigation.Screen
import com.example.testonlinestore.view.bases.OtherCard
import com.example.testonlinestore.view.profile_screen.ProfileViewModel







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
            UserDataCard(
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