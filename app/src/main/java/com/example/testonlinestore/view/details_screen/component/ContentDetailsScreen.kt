package com.example.testonlinestore.view.details_screen.component

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testonlinestore.R
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.utils.ProgressBar
import com.example.testonlinestore.utils.Resource
import com.example.testonlinestore.view.bases.CardBrand
import com.example.testonlinestore.view.bases.DescriptionText
import com.example.testonlinestore.view.bases.TitleTextToSubtitleText
import com.example.testonlinestore.view.bases.ViewPagerSlider
import com.example.testonlinestore.view.details_screen.DetailsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

// todo Раскидать по разным файлам , и сделать повторно используемыми


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


