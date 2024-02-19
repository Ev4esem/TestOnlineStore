package com.example.testonlinestore.view.details_screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.domain.model.catalog.Info

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