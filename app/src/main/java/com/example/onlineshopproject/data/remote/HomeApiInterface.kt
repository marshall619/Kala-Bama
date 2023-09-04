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

interface HomeApiInterface {
    @GET("v1/getSlider")
    suspend fun getSliders() : Response<ResponseResult<List<SliderItem>>>

    @GET("v1/getAmazingProducts")
    suspend fun getSuggested() : Response<ResponseResult<List<ProductItem>>>

    @GET("v1/getCenterBanners")
    suspend fun getCenterBanners() : Response<ResponseResult<List<SliderItem>>>

    @GET("v1/getMostDiscountedProducts")
    suspend fun getSpecialProducts() : Response<ResponseResult<List<ProductItem>>>
}