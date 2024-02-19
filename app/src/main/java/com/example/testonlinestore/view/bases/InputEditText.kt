package com.example.testonlinestore.view.bases

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.testonlinestore.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputEditText(
    modifier : Modifier = Modifier,
    title : String,
    onTitleChange : (String) -> Unit,
    @StringRes label : Int
) {


    TextField(
        value = title,
        onValueChange = { userInput ->
            onTitleChange(userInput)
        },
        trailingIcon = {

            if (title.isNotEmpty()) {
                IconButton(
                    onClick = { onTitleChange("") }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.clear_input)
                    )
                }
            }

        },
        label = {
            Text(stringResource(id = label))
        },
        isError = title.isEmpty(),
        modifier = modifier
            .width(343.dp)
            .height(51.dp)
            .clip(RoundedCornerShape(10.dp)),

        maxLines = 1

    )

}