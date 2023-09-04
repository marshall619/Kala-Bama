package com.example.onlineshopproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopproject.data.model.poductDetail.NewComment
import com.example.onlineshopproject.data.model.poductDetail.ProductDetail
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.repository.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductDetailRepository
) : ViewModel(){

    val productResponse = MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())
    val newCommentResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getProductById(productId : String){
        viewModelScope.launch{
            productResponse.emit(repository.getProductById(productId))
        }
    }

    fun setNewComment(newComment: NewComment){
        viewModelScope.launch{
            newCommentResponse.emit(repository.setNewComment(newComment))
        }
    }
}