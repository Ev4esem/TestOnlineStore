@file:OptIn(ExperimentalPagerApi::class)

package com.example.testonlinestore.view.catalog_screen.component

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.domain.model.catalog.Catalog
import com.example.testonlinestore.domain.model.catalog.CatalogList
import com.example.testonlinestore.domain.model.catalog.Feedback
import com.example.testonlinestore.domain.model.catalog.Price
import com.example.testonlinestore.presentation.navigation.Screen
import com.example.testonlinestore.utils.ProgressBar
import com.example.testonlinestore.utils.Resource
import com.example.testonlinestore.utils.SortOption
import com.example.testonlinestore.view.catalog_screen.CatalogViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Item(
    modifier : Modifier = Modifier,
    catalog : Catalog,
    onItemClick : ((Catalog) -> Unit)? = null
    ) {


    val title by remember {
        mutableStateOf(catalog.title)
    }

    val subtitle by remember {
        mutableStateOf(catalog.subtitle)
    }
    val price by remember {
        mutableStateOf(catalog.price.price)
    }
    val priceWithDiscount by remember {
        mutableStateOf(catalog.price.priceWithDiscount)
    }
    val unit by remember {
        mutableStateOf(catalog.price.unit)
    }
    val rating by remember {
        mutableStateOf(catalog.feedback.rating)
    }
    val count by remember {
        mutableStateOf(catalog.feedback.count)
    }
    val discount by remember {
        mutableStateOf(catalog.price.discount)
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
        modifier = modifier.clickable {
            onItemClick?.invoke(catalog)
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
fun OldPriceText(
    priceWithDiscount : String,
    unit : String
) {

    Row {
        Text(
            text = "$priceWithDiscount $unit",
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.grey),
            fontSize = 9.sp,
            textDecoration = TextDecoration.LineThrough
        )

    }

}

@Composable
fun DiscountText(
    modifier : Modifier = Modifier,
    discount : Int
    ) {

    Surface(
        color = colorResource(R.color.pink),
        modifier = modifier
            .padding(top = 4.dp)
            .clip(RoundedCornerShape(5.dp))
    ) {

        Text(
            text = "-$discount%",
            color = colorResource(R.color.white),
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.padding(start = 7.dp, end = 7.dp)
        )

    }

}

@Composable
fun PriceText(
    price : String,
    unit : String
) {

    Text(
        text = "$price $unit",
        style = MaterialTheme.typography.titleMedium
    )

}

@Composable
fun DescriptionItemText(
    title : String,
    subtitle : String
) {

    Column {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 12.sp
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 10.sp,
            color = colorResource(R.color.dark_grey)
        )

    }

}

@Composable
fun FeedbackText(
    modifier : Modifier = Modifier,
    count : Int,
    rating : Double
    ) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.star),
            contentDescription = "Feedback",
            tint = colorResource(id = R.color.orange),
            modifier = modifier.padding(end = 2.dp)
            )

        Text(
            text = "$rating",
            color = colorResource(R.color.orange),
            fontSize = 9.sp,
            modifier = modifier.padding(end = 2.dp)
        )

        Text(
            text = "($count)",
            color = colorResource(R.color.light_grey_component),
            fontSize = 9.sp
        )


    }

}

@Composable
fun ListProduct(
    catalogItems : CatalogList,
    navController : NavController,
    key : Int
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),

        content = {
            items(
                items = catalogItems.items,
                key = { catalog -> catalog.id + key }
                ) { catalog ->
                Item(catalog = catalog,
                    onItemClick = { navController.navigate(Screen.DetailsScreen.route + "/${catalog.id}") }
                )
            }
        },

    )

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
    Box {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .width(168.dp)
                .height(144.dp)
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
fun TopBarText() {

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.catalog),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }



}

@Composable
fun CatalogScreenContent(
    navController : NavController,
    viewModel : CatalogViewModel = hiltViewModel()
) {

    val tagsState by viewModel.tags.collectAsState()
    val catalogItemsState by viewModel.listProduct.collectAsState()
    val refreshKey = remember {
        mutableStateOf(0)
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            SortButton { sortOption ->
                viewModel.sortCatalogProducts(sortOption)
                refreshKey.value++
            }

            FilterButton()

        }



        when(catalogItemsState) {

            is Resource.Loading -> {
                ProgressBar()
            }
            is Resource.Error -> {
                Log.e("NetworkError", "Catalog items loading failed")
            }
            is Resource.Success -> {

                val catalogItems = (catalogItemsState as? Resource.Success)?.data ?: CatalogList(emptyList())
                ListProduct(catalogItems = catalogItems, navController = navController, key = refreshKey.value)
            }

        }

    }

}

@Composable
fun FilterButton() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 16.dp)
    ){
        Icon(
            painter = painterResource(R.drawable.filter_icon),
            contentDescription = stringResource(R.string.filters))

        Spacer(modifier = Modifier.width(5.dp))
        Text(text = stringResource(R.string.filters))
    }

}


// todo Неработает, когда я ее устанавливаю и
//  товары не отображаются, и теги не отображаются
@Composable
fun TagList(
    tags : List<String>,
    selected : Set<String>,
    onTagClicked : (String) -> Unit
    ) {

    LazyRow() {
        items(tags) { tag ->
            TagItem(tag = tag, selected = selected.contains(tag)) {
                onTagClicked(it)
            }
        }
    }

}

@Composable
fun TagItem(
    tag : String,
    selected : Boolean,
    onTagClicked : (String) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = colorResource(if (selected) R.color.dark_blue else R.color.light_grey_background),
                shape = RoundedCornerShape(20.dp)
                )

    ) {

        Text(
            text = tag,
            modifier = Modifier
                .clickable { onTagClicked(tag) }
                .padding(start = 10.dp, end = 10.dp),
            color = colorResource(if (selected) R.color.white else R.color.grey),
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Light else FontWeight.Bold

        )
        if (selected) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "",
                tint = colorResource(R.color.white),
                modifier = Modifier
                    .height(15.dp)
                    .padding(end = 10.dp)
                )
        }

    }

}

@Composable
fun SortButton(
    onSortSelected: (SortOption) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedOption by remember {
        mutableStateOf(SortOption.POPULARITY)
    }

    Box(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.sort_action_icon),
                contentDescription = stringResource(R.string.sort))
            Spacer(modifier = Modifier.width(9.dp))
            Text(
                text = when(selectedOption) {
                    SortOption.POPULARITY -> stringResource(R.string.popularity)
                    SortOption.PRICE_LOW_TO_HIGH -> stringResource(R.string.price_low_to_high)
                    SortOption.PRICE_HIGH_TO_LOW -> stringResource(R.string.price_high_to_low)
                    },
                modifier = Modifier.clickable { expanded = true }
                )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                DropdownMenuItem(
                    onClick = {
                        selectedOption = SortOption.POPULARITY
                        onSortSelected(selectedOption)
                        expanded = false
                    },
                ) {
                    Text(text = stringResource(R.string.popularity))
                }
                DropdownMenuItem(
                    onClick = {
                        selectedOption = SortOption.PRICE_LOW_TO_HIGH
                        onSortSelected(selectedOption)
                        expanded = false
                    },
                ) {
                    Text(text = stringResource(R.string.price_low_to_high))
                }

                DropdownMenuItem(
                    onClick = {
                        selectedOption = SortOption.PRICE_HIGH_TO_LOW
                        onSortSelected(selectedOption)
                        expanded = false
                    },
                ) {
                    Text(text = stringResource(R.string.price_high_to_low))
                }

            }
        }
    }

}


@Preview
@Composable
fun PrevCatalog() {
    Column {
        val catalog = Catalog(
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
        Item(catalog = catalog, onItemClick = {null})
        ViewPagerSlider()

        Spacer(modifier = Modifier.height(20.dp))

        FilterButton()
        Spacer(modifier = Modifier.height(20.dp))
        SortButton(onSortSelected = {

        })

        TagItem(tag = "Смотреть все", selected = true, onTagClicked = { })

    }
}