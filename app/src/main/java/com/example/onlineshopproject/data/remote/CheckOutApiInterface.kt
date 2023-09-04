package com.example.onlineshopproject.data.remote

import com.example.onlineshopproject.data.model.ResponseResult
import com.example.onlineshopproject.data.model.checkOut.ConfirmPurchase
import com.example.onlineshopproject.data.model.checkOut.OrderDetail
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.model.home.SliderItem
import com.example.onlineshopproject.data.model.profile.LoginResponse
import com.example.onlineshopproject.data.model.profile.ProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CheckOutApiInterface {

    @POST("v1/setNewOrder")
    suspend fun setNewOrder(
        @Body orderRequest : OrderDetail
    ) : Response<ResponseResult<String>>

    @POST("v1/confirmPurchase")
    suspend fun confirmPurchase(
        @Body confirmPurchase: ConfirmPurchase
    ): Response<ResponseResult<String>>

}