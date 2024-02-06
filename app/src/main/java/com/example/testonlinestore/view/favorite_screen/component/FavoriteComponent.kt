package com.example.testonlinestore.view.favorite_screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.view.catalog_screen.component.DescriptionItemText
import com.example.testonlinestore.view.catalog_screen.component.DiscountText
import com.example.testonlinestore.view.catalog_screen.component.FeedbackText
import com.example.testonlinestore.view.catalog_screen.component.OldPriceText
import com.example.testonlinestore.view.catalog_screen.component.PriceText
import com.example.testonlinestore.view.catalog_screen.component.ViewPagerSlider
import com.example.testonlinestore.view.favorite_screen.FavoriteViewModel
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
fun FavoriteContent() {
    val tabItems = listOf("Товары","Бренды")
    Column(
        modifier = Modifier.padding(21.dp)
    ) {
        TopBarFavorite()
        TabLayout(tabs = tabItems)
        ListItemFavorite()
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ItemFavorite(
    modifier : Modifier = Modifier,
    cardItem : CardItem
) {

    val title by remember {
        mutableStateOf(cardItem.title)
    }

    val subtitle by remember {
        mutableStateOf(cardItem.subtitle)
    }
    val price by remember {
        mutableStateOf(cardItem.price)
    }
    val priceWithDiscount by remember {
        mutableStateOf(cardItem.priceWithDiscount)
    }
    val unit by remember {
        mutableStateOf(cardItem.unit)
    }
    val rating by remember {
        mutableStateOf(cardItem.rating)
    }
    val count by remember {
        mutableStateOf(cardItem.count)
    }
    val discount by remember {
        mutableStateOf(cardItem.discount)
    }



    Card(
        modifier = modifier
            .padding(8.dp)
            .height(292.dp)
            .width(168.dp)
        ,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.white)
        )
    ) {



        Box() {
            ViewPagerSlider()
            Box(
                modifier = modifier
                    .align(Alignment.TopEnd)
                    .fillMaxWidth()

            ) {

                IconButton(

                    onClick = { /*TODO*/ },
                    modifier = modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.heart_icon),
                        contentDescription = stringResource(R.string.favorite),
                        tint = colorResource(id = R.color.pink)
                    )
                }



            }

        }



        Box(
        ) {
            Column(
                modifier = modifier
                    .padding(start = 5.dp)
                    .fillMaxSize()
                ,
                verticalArrangement = Arrangement.Bottom
            ) {
                OldPriceText(
                    priceWithDiscount = priceWithDiscount,
                    unit = unit,
                )


                Row {

                    PriceText(
                        price = price,
                        unit = unit
                    )
                    Spacer(modifier = modifier.width(5.dp))
                    DiscountText(
                        discount = discount
                    )


                }


                DescriptionItemText(
                    title = title,
                    subtitle = subtitle
                )
                Spacer(modifier = modifier.height(6.dp))
                FeedbackText(
                    count = count,
                    rating = rating
                )

                Box(
                    modifier = modifier
                        .align(Alignment.End)
                        .background(
                            color = colorResource(id = R.color.pink),
                            shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                        )
                        .height(32.dp)
                        .width(32.dp)

                    ,
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        painter = painterResource(R.drawable.plus_icon),
                        tint = colorResource(R.color.white),
                        contentDescription = "Add",

                        )

                }
            }
        }




    }

}

@Composable
fun TopBarFavorite() {
    Row() {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.exit))
        Spacer(modifier = Modifier.width(28.dp))
        Text(
            text = stringResource(R.string.favorite),
            fontSize = 16.sp
            )
    }


}

@Composable
fun TabLayout(
    tabs: List<String>
) {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = colorResource(R.color.light_grey_background),
        indicator = {
            Box(
                modifier = Modifier
                    .height(0.dp)
                    .width(0.dp)
            )
        },
        modifier = Modifier.padding(5.dp).clip(RoundedCornerShape(10.dp))
        ) {

        tabs.forEachIndexed { index, text ->
            val selected = selectedTabIndex == index
            Tab(
                selected = (selected),
                onClick = { selectedTabIndex = index },
                modifier = Modifier
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
                    .background(
                        color = colorResource(
                            if (selected) R.color.white else R.color.light_grey_background
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                text = {
                    Text(
                        text = text,
                        color = colorResource(
                            if (selected) R.color.black else R.color.grey
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            )
        }

    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ListItemFavorite(
    viewModel : FavoriteViewModel = hiltViewModel()
) {

    val cardItems by viewModel.cardProducts.collectAsState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),

            content = {
                items(
                    items = cardItems
                ) { catalog ->
                    ItemFavorite(cardItem = catalog)
                }
            },
        )

}

@Preview
@Composable
fun PrevFavoriteScreen() {

    val list = listOf("Бренды","Товары")


}