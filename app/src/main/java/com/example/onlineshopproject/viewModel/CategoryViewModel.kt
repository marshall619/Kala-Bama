package com.example.onlineshopproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel(){

    val amazingItems = MutableStateFlow<NetworkResult<List<ProductItem>>>(NetworkResult.Loading())
    val superMarketItems = MutableStateFlow<NetworkResult<List<ProductItem>>>(NetworkResult.Loading())
    val mostDiscountedItems = MutableStateFlow<NetworkResult<List<ProductItem>>>(NetworkResult.Loading())
    val bestsellerItems = MutableStateFlow<NetworkResult<List<ProductItem>>>(NetworkResult.Loading())
    val mostVisitedItems = MutableStateFlow<NetworkResult<List<ProductItem>>>(NetworkResult.Loading())
    val mostFavoriteItems = MutableStateFlow<NetworkResult<List<ProductItem>>>(NetworkResult.Loading())

    fun getAmazingItemsApiCall(){
        viewModelScope.launch {
            amazingItems.emit(repository.getAmazingProducts())
        }
    }
    fun getSuperMarketItemsApiCall(){
        viewModelScope.launch {
            superMarketItems.emit(repository.getSuperMarketAmazingProducts())
        }
    }
    fun getMostDiscountedItemsApiCall(){
        viewModelScope.launch {
            mostDiscountedItems.emit(repository.getMostDiscountedProducts())
        }
    }
    fun getBestsellerItemsApiCall(){
        viewModelScope.launch {
            bestsellerItems.emit(repository.getBestsellerProducts())
        }
    }
    fun getMostVisitedItemsApiCall(){
        viewModelScope.launch {
            mostVisitedItems.emit(repository.getMostVisitedProducts())
        }
    }
    fun getMostFavoriteItemsApiCall(){
        viewModelScope.launch {
            mostFavoriteItems.emit(repository.getMostFavoriteProducts())
        }
    }

}