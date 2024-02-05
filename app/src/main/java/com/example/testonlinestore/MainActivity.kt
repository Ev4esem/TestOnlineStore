package com.example.testonlinestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.testonlinestore.presentation.navigation.BottomNavigationBar
import com.example.testonlinestore.presentation.navigation.Navigation
import com.example.testonlinestore.ui.theme.TestOnlineStoreTheme
import com.example.testonlinestore.view.catalog_screen.CatalogScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestOnlineStoreTheme {

                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface {



                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        },
                        content = { padding ->

                            Navigation(
                                navController = navController,
                                padding = padding,
                                context = this@MainActivity
                            )

                        }
                    )
                }
            }
        }
    }
}
