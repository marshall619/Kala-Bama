package com.example.onlineshopproject.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopproject.data.model.home.SliderItem
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SetLoadingStatus(viewModel: HomeViewModel = hiltViewModel(), onLoading : (Boolean) -> Unit){
    var sliderList by remember { mutableStateOf(emptyList<SliderItem>()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        viewModel.sliders.collectLatest { sliderResult ->
            when (sliderResult) {
                is NetworkResult.Success -> {
                    sliderResult.data?.let {
                        sliderList = it
                    }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("6191", "Top slider api ${sliderResult.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }
    onLoading(loading)
}