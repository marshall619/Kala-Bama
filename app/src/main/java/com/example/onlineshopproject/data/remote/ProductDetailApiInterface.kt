package com.example.onlineshopproject.data.remote

import com.example.onlineshopproject.data.model.poductDetail.ProductDetail
import com.example.onlineshopproject.data.model.ResponseResult
import com.example.onlineshopproject.data.model.poductDetail.NewComment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductDetailApiInterface {
    @GET("v1/getProductById")
    suspend fun getProductById(
        @Query("id") productId: String
    ) : Response<ResponseResult<ProductDetail>>

    @POST("v1/setNewComment")
    suspend fun setNewComment(
        @Body newComment: NewComment
    ): Response<ResponseResult<String>>
}