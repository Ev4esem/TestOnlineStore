package com.example.testonlinestore.view.catalog_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testonlinestore.view.catalog_screen.CatalogViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

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