package com.example.onlineshopproject.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopproject.data.model.basket.CartDetails
import com.example.onlineshopproject.data.model.basket.CartItem
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.repository.BasketRepository
import com.example.onlineshopproject.repository.CategoryRepository
import com.example.onlineshopproject.util.DigitHelper.applyDiscount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: BasketRepository
) : ViewModel(){

    val cartItems = repository.cartItem
    val cartItemCount = repository.cartItemCount
    val cartDetails = MutableStateFlow(CartDetails(0,0,0,0))
    var payablePrice = mutableStateOf(0L)

    fun insertCartItem(newItem: CartItem) {
        viewModelScope.launch {
            repository.insertCartItem(newItem)
        }
    }

    fun removeFromCart(deleteItem: CartItem) {
        viewModelScope.launch {
            repository.removeFromCart(deleteItem)
        }
    }

    fun changeCountCartItem(id : String , newCount : Int) {
        viewModelScope.launch {
            repository.changeCountCartItem(id,newCount)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch {
            repository.deleteAllItems()
        }
    }

    private fun calculateCartDetails(items : List<CartItem>){
        var totalCount = 0
        var totalPrice = 0L
        var totalDiscount = 0L
        var payablePrice = 0L
        items.forEach { item ->
            totalPrice += item.price * item.count
            payablePrice += applyDiscount(item.price , item.discountPercent) * item.count
            totalCount += item.count
        }
        totalDiscount += totalPrice - payablePrice
        cartDetails.value = CartDetails(totalCount,totalPrice,totalDiscount,payablePrice)
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                repository.cartItem.collectLatest { cartItems ->
                    calculateCartDetails(cartItems)
                }
            }
        }

    }

}