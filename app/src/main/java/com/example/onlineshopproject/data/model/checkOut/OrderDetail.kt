package com.example.onlineshopproject.data.model.checkOut

import com.example.onlineshopproject.data.model.basket.CartItem

data class OrderDetail(
    val token: String,
    val orderAddress: String,
    val orderTotalDiscount: Long,
    val orderTotalPrice: Long,
    val orderUserName: String,
    val orderUserPhone: String,
    val orderProducts : List<CartItem>
)
