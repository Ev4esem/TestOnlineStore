@file:OptIn(ExperimentalPagerApi::class)

package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.Feedback
import com.example.testonlinestore.domain.model.catalog.Price
import com.example.testonlinestore.domain.model.favorite.CardItem
import com.example.testonlinestore.view.favorite_screen.FavoriteViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Item(
    modifier : Modifier = Modifier,
    id : String,
    title : String,
    subtitle : String,
    price : String,
    priceWithDiscount : String,
    unit : String,
    rating : Double,
    count : Int,
    discount : Int,
    onItemClick : ((String) -> Unit)? = null,
    viewModel : FavoriteViewModel = hiltViewModel()
) {


    var selectedFavoriteButton by rememberSaveable {
        mutableStateOf(false)
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

                onClick = {
                    selectedFavoriteButton = !selectedFavoriteButton
                    val cardItem = CardItem(
                        id = id,
                        count = count,
                        rating = rating,
                        discount = discount,
                        price = price,
                        priceWithDiscount = priceWithDiscount,
                        unit = unit,
                        subtitle = subtitle,
                        title = title
                    )
                    if (selectedFavoriteButton) {
                        viewModel.insertCardItem(cardItem)

                    } else {
                        viewModel.deleteCardItem(cardItem)
                    }



                  },
                modifier = modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    // todo При переходе на другой экран состояние не сохраняется
                    painter = painterResource(
                        if (selectedFavoriteButton) {
                            R.drawable.heart_fill_icon
                        } else {
                            R.drawable.heart_icon
                        }
                    ),
                    contentDescription = stringResource(R.string.favorite),
                    tint = colorResource(id = R.color.pink)
                )
            }



        }

    }



    Box(
        modifier = modifier.clickable {
            //todo Может не работать
            onItemClick?.invoke(id)
        }
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
                unit = unit
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
                    // todo Добавить ресурс
                    contentDescription = "Add",

                    )

            }
        }
    }

    }

}


@Preview
@Composable
fun PrevCatalog() {

    val mock = Catalog(
        0,
        "ssssssssssssssssssssssss",
        Feedback(
            5,
            2.4
        ),
        "252895252",
        listOf(),
        "2784278482",
        Price(
            23,
            "245",
            "200",
            "$"
        ),
        "Ghgjshjkghakjghaksjgkasg",
        listOf(),
        "Rashid"
    )

    Column {

        ViewPagerSlider()

        Spacer(modifier = Modifier.height(20.dp))

        FilterButton()
        Spacer(modifier = Modifier.height(20.dp))
        SortButton(onSortSelected = {

        })

        TagItem(tag = "Смотреть все", selected = true, onTagClicked = { })

    }
}