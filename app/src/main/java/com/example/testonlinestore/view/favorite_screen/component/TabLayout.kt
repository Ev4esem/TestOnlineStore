package com.example.testonlinestore.view.favorite_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R

@Composable
fun TabLayout(
    tabs: List<String>
) {

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = colorResource(R.color.light_grey_background),
        indicator = {
            Box(
                modifier = Modifier
                    .height(0.dp)
                    .width(0.dp)
            )
        },
        modifier = Modifier.padding(5.dp).clip(RoundedCornerShape(10.dp))
    ) {

        tabs.forEachIndexed { index, text ->
            val selected = selectedTabIndex == index
            Tab(
                selected = (selected),
                onClick = { selectedTabIndex = index },
                modifier = Modifier
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
                    .background(
                        color = colorResource(
                            if (selected) R.color.white else R.color.light_grey_background
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                text = {
                    Text(
                        text = text,
                        color = colorResource(
                            if (selected) R.color.black else R.color.grey
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            )
        }

    }

}