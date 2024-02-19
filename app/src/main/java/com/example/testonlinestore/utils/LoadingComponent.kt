package com.example.testonlinestore.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.testonlinestore.R

@Composable
fun ProgressBar() {

    val progressValue = 1f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,animationSpec = infiniteRepeatable(animation = tween(1000)))

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),

    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.pink),
            progress = progressAnimationValue,
            modifier = Modifier

        )
    }
}



@Preview
@Composable
fun ProgressBarPrev() {

    ProgressBar()

}
