package com.example.onlineshopproject.data.model.home

data class ProductItem(
    val _id: String,
    val discountPercent: Int,
    val image: String,
    val name: String,
    val price: Long,
    val seller: String
)