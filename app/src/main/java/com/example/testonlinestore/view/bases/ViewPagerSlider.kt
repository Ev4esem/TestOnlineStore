package com.example.testonlinestore.view.bases

import android.annotation.SuppressLint
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
import com.example.testonlinestore.domain.model.ImageProduct
import com.example.testonlinestore.view.catalog_screen.CatalogUiState
import com.example.testonlinestore.view.catalog_screen.CatalogViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(
    imageProducts : List<ImageProduct>,
    @SuppressLint("ModifierParameter") modifier : Modifier = Modifier
) {

    val pagerState = rememberPagerState(
        pageCount = imageProducts.size,
        initialPage = 0
    )
    Box(
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
            ,

            ) { index ->

            if (index in imageProducts.indices) {
                Image(
                    painter = painterResource(id = imageProducts[index].image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }



        }
    }



}