package com.example.onlineshopproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopproject.data.model.checkOut.ConfirmPurchase
import com.example.onlineshopproject.data.model.checkOut.OrderDetail
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.repository.CheckOutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val repository: CheckOutRepository
) : ViewModel(){


    val orderResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun setNewOrder(orderRequest : OrderDetail){
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                orderResponse.emit(repository.setNewOrder(orderRequest))
            }
        }
    }

    val purchaseResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun confirmPurchase(confirmPurchase: ConfirmPurchase) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                purchaseResponse.emit(repository.confirmPurchase(confirmPurchase))
            }
        }
    }


}