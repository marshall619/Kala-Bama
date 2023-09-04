package com.example.onlineshopproject.repository

import com.example.onlineshopproject.data.model.checkOut.ConfirmPurchase
import com.example.onlineshopproject.data.model.checkOut.OrderDetail
import com.example.onlineshopproject.data.remote.BaseApiResponse
import com.example.onlineshopproject.data.remote.CheckOutApiInterface
import com.example.onlineshopproject.data.remote.NetworkResult
import javax.inject.Inject

class CheckOutRepository @Inject constructor(
    private val api: CheckOutApiInterface,
) : BaseApiResponse() {

    suspend fun setNewOrder(orderRequest : OrderDetail): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(orderRequest)
        }

    suspend fun confirmPurchase(confirmPurchase: ConfirmPurchase): NetworkResult<String> =
        safeApiCall {
            api.confirmPurchase(confirmPurchase)
        }


}