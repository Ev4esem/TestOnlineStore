package com.example.testonlinestore.view.bases

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.view.catalog_screen.component.DescriptionItemText
import com.example.testonlinestore.view.catalog_screen.component.DiscountText
import com.example.testonlinestore.view.catalog_screen.component.FeedbackText
import com.example.testonlinestore.view.catalog_screen.component.OldPriceText
import com.example.testonlinestore.view.catalog_screen.component.PriceText
import com.google.accompanist.pager.ExperimentalPagerApi


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
            com.example.testonlinestore.view.catalog_screen.component.ViewPagerSlider()
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