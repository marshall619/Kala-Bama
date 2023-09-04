package com.example.onlineshopproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopproject.ui.components.AppConfig
import com.example.onlineshopproject.ui.theme.OnlineShopProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnlineShopProjectTheme {
                navController = rememberNavController()
                AppConfig(navController)
            }
        }
    }
}
