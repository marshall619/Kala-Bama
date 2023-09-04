package com.example.onlineshopproject.data.remote

import com.example.onlineshopproject.data.model.ResponseResult
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.model.home.SliderItem
import com.example.onlineshopproject.data.model.profile.LoginResponse
import com.example.onlineshopproject.data.model.profile.ProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryApiInterface {

    @GET("v1/getAmazingProducts")
    suspend fun getAmazingProducts() : Response<ResponseResult<List<ProductItem>>>

    @GET("v1/getSuperMarketAmazingProducts")
    suspend fun getSuperMarketAmazingProducts() : Response<ResponseResult<List<ProductItem>>>

    @GET("v1/getMostDiscountedProducts")
    suspend fun getMostDiscountedProducts() : Response<ResponseResult<List<ProductItem>>>

    @GET("v1/getBestsellerProducts")
    suspend fun getBestsellerProducts() : Response<ResponseResult<List<ProductItem>>>

    @GET("v1/getMostVisitedProducts")
    suspend fun getMostVisitedProducts() : Response<ResponseResult<List<ProductItem>>>

    @GET("v1/getMostFavoriteProducts")
    suspend fun getMostFavoriteProducts() : Response<ResponseResult<List<ProductItem>>>

}