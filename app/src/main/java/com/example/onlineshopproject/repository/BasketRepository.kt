package com.example.onlineshopproject.repository

import com.example.onlineshopproject.data.db.CartDao
import com.example.onlineshopproject.data.model.basket.CartItem
import com.example.onlineshopproject.data.remote.BaseApiResponse
import javax.inject.Inject

class BasketRepository @Inject constructor(
    private val dao: CartDao,
) : BaseApiResponse() {

    val cartItem = dao.getAllItems()
    val cartItemCount = dao.getCartItemCount()

    suspend fun insertCartItem(newItem: CartItem) {
        dao.insertCartItem(newItem)
    }

    suspend fun removeFromCart(deleteItem: CartItem) {
        dao.removeFromCart(deleteItem)
    }

    suspend fun changeCountCartItem(id : String , newCount : Int) {
        dao.changeCountCartItem(id , newCount)
    }

    suspend fun deleteAllItems() {
        dao.deleteAllItems()
    }



}