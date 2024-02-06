package com.example.testonlinestore.view.details_screen.component

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.Info
import com.example.testonlinestore.utils.ProgressBar
import com.example.testonlinestore.utils.Resource
import com.example.testonlinestore.view.catalog_screen.CatalogViewModel
import com.example.testonlinestore.view.details_screen.DetailsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentDetailsScreen(
    navController : NavController,
    catalogId : String,
    viewModel : DetailsViewModel = hiltViewModel()
    ) {

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var catalogState by remember {
        mutableStateOf<Resource<Catalog>>(Resource.Loading())
    }

    LaunchedEffect(key1 = catalogId) {
        viewModel.getCatalogById(catalogId = catalogId) { result ->
            catalogState = result
        }
    }


        when(val catalogResult = catalogState) {
            is Resource.Loading -> {
                ProgressBar()
            }
            is Resource.Success -> {
                val catalog = catalogResult.data


                Column(
                   modifier = Modifier
                       .padding(16.dp)
                       .verticalScroll(state = scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TopBarDetails()
                    Box(
                            contentAlignment = Alignment.Center
                        ) {
                            
                            ViewPagerSlider()

                            Box(
                                modifier = Modifier.align(Alignment.TopEnd)
                            ) {
                                FavoriteIcon()

                            }
                            Box(
                                modifier = Modifier.align(Alignment.BottomStart)
                            ){
                                Icon(
                                    painter = painterResource(R.drawable.question_icon),
                                    tint = colorResource(R.color.grey),
                                    contentDescription = "")
                            }
                        }
                        Spacer(modifier = Modifier.height(42.dp))
                        TitleTextToSubtitleText(
                            title = catalog?.title.toString(),
                            subtitle = catalog?.subtitle.toString()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        CountProduct(
                            count = catalog?.available ?: 0
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        FeedbackText(
                            rating = catalog?.feedback?.rating?.toInt() ?: 0,
                            count = catalog?.feedback?.count ?: 0
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            PriceProductText(
                                price = catalog?.price?.price.toString(),
                                priceWithDiscount = catalog?.price?.priceWithDiscount.toString(),
                                unit = catalog?.price?.unit.toString()
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            DiscountText(
                                discount = catalog?.price?.discount ?: 0
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))

                        DifferentText(title = R.string.description)
                    Spacer(modifier = Modifier.height(16.dp))

                    CardBrand(
                            title = catalog?.title.toString()
                        )
                    Spacer(modifier = Modifier.height(8.dp))

                        DescriptionText(
                            description = catalog?.description.toString()
                        )
                    Spacer(modifier = Modifier.height(60.dp))
                    DifferentText(title = R.string.characteristics)
                    Spacer(modifier = Modifier.height(16.dp))
                    ListCharacteristic(
                        info = catalog?.info ?: emptyList()
                    )
                    Spacer(modifier = Modifier.height(34.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        DifferentText(title = R.string.structure)
                        Icon(
                            painter = painterResource(id = R.drawable.copy_icon),
                            contentDescription = "Copy")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    StructureText(
                        title = catalog?.ingredients.toString()
                    )
                    Spacer(modifier = Modifier.height(58.dp))
                    AddProductButton(
                        price = catalog?.price?.price.toString(),
                        priceWithDiscount = catalog?.price?.priceWithDiscount.toString(),
                        unit = catalog?.price?.unit.toString())

                }


            }
            is Resource.Error -> {
                val errorMessage = catalogResult.message ?: "An error occurred"
                Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show()
            }
        }




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

@Composable
fun TopBarDetails() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 8.dp, top = 14.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "")

        Icon(
            painter = painterResource(R.drawable.shape_icon),
            contentDescription = "")

    }

}

@Composable
fun FavoriteIcon() {

    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Icon(
            painter = painterResource(R.drawable.heart_icon),
            contentDescription = stringResource(R.string.favorite),
            tint = colorResource(id = R.color.pink)
        )
    }

}

@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(
    viewModel : CatalogViewModel = hiltViewModel()
) {
    val imageProduct = viewModel.getImageProducts()


    val pagerState = rememberPagerState(
        pageCount = imageProduct.size,
        initialPage = 0
    )
    Box(
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .width(355.dp)
                .height(368.dp)
            ,

            ) { index ->

            if (index in imageProduct.indices) {
                Image(
                    painter = painterResource(id = imageProduct[index].image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }



        }
    }



}

@Composable
fun TitleTextToSubtitleText(
    title : String,
    subtitle : String
) {
    Column {
        Text(
            text = title,
            color = colorResource(id = R.color.grey),
            fontSize = 16.sp
        )

        Text(
            text = subtitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }




}

@Composable
fun CountProduct(
    count : Int
) {

    val nameCount = when {
        count == 0 -> "штук"
        count == 1 -> "штука"
        count < 4 -> "штуки"
        else -> "штук"
    }
    val nameState by remember {
        mutableStateOf(nameCount)
    }

    Text(
        text = "Доступно для заказа $count $nameState ",
        fontSize = 12.sp,
        color = colorResource(R.color.grey),
        modifier = Modifier.fillMaxWidth()
    )

}

@Composable
fun FeedbackText(
    rating : Int,
    count : Int
) {
    val nameFeedback = when {
        count == 0 -> "отзывов"
        count == 1 -> "отзыв"
        count < 4 -> "штуки"
        else -> "отзывов"
    }
    val nameState by remember {
        mutableStateOf(nameFeedback)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RatingBar(rating = rating)
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "$rating",
            color = colorResource(id = R.color.grey),
            fontSize = 12.sp
            )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "* $count $nameState",
            color = colorResource(id = R.color.grey),
            fontSize = 12.sp
            )
    }
    
}

@Composable
fun RatingBar(rating: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            val isFilled = index < rating
            Star(isFilled)
        }
    }
}

@Composable
fun Star(isFilled: Boolean) {
    val starColor = if (isFilled) R.color.orange else R.color.grey
    Icon(
        painter = painterResource(id = R.drawable.star),
        contentDescription = null,
        tint = colorResource(starColor)
    )
}

@Composable
fun PriceProductText(
    price : String,
    priceWithDiscount : String,
    unit : String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$price $unit",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = "$priceWithDiscount $unit",
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.grey),
            fontSize = 12.sp,
            textDecoration = TextDecoration.LineThrough
        )

    }


}

@Composable
fun DiscountText(
    discount : Int
) {


        Surface(
            color = colorResource(R.color.pink),
            modifier = Modifier
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(5.dp))
        ) {

            Text(
                text = "-$discount%",
                color = colorResource(R.color.white),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 7.dp, end = 7.dp)
            )

        }


}

@Composable
fun CardBrand(
    title : String,
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


            Column(
                modifier = modifier.weight(1f)
            ) {

                Text(
                    text = title,
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
fun DescriptionText(
    description : String
) {

    Text(
        text = description,
        fontSize = 12.sp,
        color = colorResource(R.color.dark_grey),
    )

}

@Composable
fun ListCharacteristic(
    info : List<Info>
) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            items(info) { item ->
                ItemCharacteristic(
                    title = item.title, info = item.value)
            }
        }
}

@Composable
fun ItemCharacteristic(
    title : String,
    info : String
) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,

                )
            Text(
                text = info
            )
        }



}

@Composable
fun StructureText(
    title : String
) {

    Text(
        text = title
    )

}

@Composable
fun AddProductButton(
    price : String,
    priceWithDiscount : String,
    unit : String
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.pink))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "$price$unit",
                    color = colorResource(R.color.white),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "$priceWithDiscount $unit",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(R.color.grey),
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.LineThrough
                )

            }

            Text(
                text = stringResource(R.string.add_card),
                color = colorResource(R.color.white),
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 16.dp)
            )

        }




    }
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


