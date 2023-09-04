package com.example.onlineshopproject.ui.Screens.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.R
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.components.OurLoading
import com.example.onlineshopproject.util.Constants
import com.example.onlineshopproject.viewModel.DataStoreViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashSection(navController: NavController, dataStore: DataStoreViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(Constants.LOGO_SIZE)
        )
        LaunchedEffect(true) {
            delay(2000)
            if (!dataStore.getUserTOKEN().isNullOrBlank()) {
                navController.navigate(Screens.Home.route) {
                    popUpTo(Screens.Splash.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(Screens.Login.route) {
                    popUpTo(Screens.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        OurLoading(height = 100.dp, isDark = true)
    }
}