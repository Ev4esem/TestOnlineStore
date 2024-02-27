package com.example.testonlinestore.view.details_screen.component

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Feedback
import com.example.testonlinestore.domain.model.catalog.Info
import com.example.testonlinestore.domain.model.catalog.Price
import com.example.testonlinestore.view.bases.CardBrand
import com.example.testonlinestore.view.bases.DescriptionText
import com.example.testonlinestore.view.bases.TitleTextToSubtitleText
import com.example.testonlinestore.view.bases.ViewPagerSlider
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentDetailsScreen(
    available : Int,
    description : String,
    feedback : Feedback,
    info : List<Info>,
    ingredients : String,
    price : Price,
    subtitle : String,
    title : String,
    imageProducts : List<ImageProduct>,
    isFavorite : Boolean,
    onClickFavorite : () -> Unit,
    onClickBack : () -> Unit
) {

    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current


    DisposableEffect(focusManager) {
        focusManager.clearFocus(true)
        onDispose { }
    }



    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopBarDetails(
            onClickBack = onClickBack
        )
        Box(
            contentAlignment = Alignment.Center
        ) {

            ViewPagerSlider(
                modifier = Modifier
                    .width(355.dp)
                    .height(368.dp),
                imageProducts = imageProducts
            )

            Box(
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                FavoriteButton(
                    onClickFavorite = onClickFavorite,
                    isFavorite = isFavorite
                )

            }
            Box(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                //todo добавить ресурс
                Icon(
                    painter = painterResource(R.drawable.question_icon),
                    tint = colorResource(R.color.grey),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(42.dp))
        TitleTextToSubtitleText(
            title = title,
            subtitle = subtitle
        )
    }


    Spacer(modifier = Modifier.height(10.dp))
    CountProduct(
        count = available
    )
    Spacer(modifier = Modifier.height(20.dp))
    FeedbackText(
        rating = feedback.rating.toInt(),
        count = feedback.count
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(modifier = Modifier.fillMaxWidth()) {
        PriceProductText(
            price = price.price,
            priceWithDiscount = price.priceWithDiscount,
            unit = price.unit
        )

        Spacer(modifier = Modifier.width(10.dp))

        DiscountText(
            discount = price.discount
        )
    }
    Spacer(modifier = Modifier.height(24.dp))

    DifferentText(title = R.string.description)
    Spacer(modifier = Modifier.height(16.dp))

    CardBrand(
        title = title
    )
    Spacer(modifier = Modifier.height(8.dp))

    DescriptionText(
        description = description
    )
    Spacer(modifier = Modifier.height(60.dp))
    DifferentText(title = R.string.characteristics)
    Spacer(modifier = Modifier.height(16.dp))
    ListCharacteristic(
        info = info
    )
    Spacer(modifier = Modifier.height(34.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DifferentText(title = R.string.structure)
            //todo добавить ресурс
        Icon(
            painter = painterResource(id = R.drawable.copy_icon),
            contentDescription = "Copy"
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    StructureText(
        title = ingredients
    )
    Spacer(modifier = Modifier.height(58.dp))
    AddProductButton(
        price = price.price,
        priceWithDiscount = price.priceWithDiscount,
        unit = price.unit
    )

}


@Composable
fun DifferentText(
    title : Int
) {

    Text(
        text = stringResource(title),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )

}


@Preview
@Composable
fun DetailsScreenPrev() {
    Column {
        CardBrand(title = "ROCYN")
        DiscountText(discount = 20)
        FeedbackText(rating = 4, count = 10)
        ItemCharacteristic(title = "Артикул товара", info = "133987")
        AddProductButton(price = "430", priceWithDiscount = "550", unit = "$")
    }
}


