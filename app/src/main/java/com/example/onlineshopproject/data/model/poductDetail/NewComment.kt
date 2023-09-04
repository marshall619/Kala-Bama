package com.example.onlineshopproject.data.model.poductDetail

data class NewComment(
    val token: String,
    val productId: String,
    val star: Int,
    val title: String,
    val description: String,
    val userName: String
)