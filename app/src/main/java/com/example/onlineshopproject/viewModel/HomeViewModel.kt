package com.example.onlineshopproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.model.home.SliderItem
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel(){

    val sliders = MutableStateFlow<NetworkResult<List<SliderItem>>>(NetworkResult.Loading())
    val suggestedList = MutableStateFlow<NetworkResult<List<ProductItem>>>(NetworkResult.Loading())
    val centerBanner = MutableStateFlow<NetworkResult<List<SliderItem>>>(NetworkResult.Loading())
    val specialItemList = MutableStateFlow<NetworkResult<List<ProductItem>>>(NetworkResult.Loading())

    fun getAllHomeApiCalls(){
        viewModelScope.launch {
            launch {
                sliders.emit(repository.getSliders())
            }

            launch {
                suggestedList.emit(repository.getSuggested())
            }

            launch {
                centerBanner.emit(repository.getCenterBanners())
            }
            launch {
                specialItemList.emit(repository.getSpecialProducts())
            }
        }
    }


}