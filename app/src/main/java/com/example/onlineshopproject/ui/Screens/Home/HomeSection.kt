package com.example.onlineshopproject.ui.Screens.Home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.home.SliderItem
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.components.HandLoading
import com.example.onlineshopproject.ui.components.SetLoadingStatus
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeSection(navController: NavController) {
    Home(navController)
}

@Composable
private fun Home(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    var loading by remember { mutableStateOf(true) }

    //refresh all api calls
    LaunchedEffect(true) {
        refreshAllTokens(viewModel = viewModel)
    }
    //loading status
    SetLoadingStatus { loading = it }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        LazyColumn() {
            item { TopSliderSection(navController = navController) }
            item { ShowCaseSection(navController) }
            item { CenterBannerSection(1){navController.navigate(Screens.WebView.route + "?url=https://ghazaland.com/")} }
            item { SuggestedForYou(navController) }
            item { CenterBannerSection(3){navController.navigate(Screens.WebView.route + "?url=https://shalmod.com/")} }
            item { SpecialItemsSection(navController) }
        }
    }
}

private fun refreshAllTokens(viewModel: HomeViewModel) {
    viewModel.getAllHomeApiCalls()
}

