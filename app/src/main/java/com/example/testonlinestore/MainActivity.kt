package com.example.testonlinestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.testonlinestore.presentation.navigation.AppScreen
import com.example.testonlinestore.ui.theme.TestOnlineStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestOnlineStoreTheme {
                Surface {

                    AppScreen()

                }
            }
        }
    }
}
