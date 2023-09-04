package com.example.onlineshopproject.repository

import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.model.home.SliderItem
import com.example.onlineshopproject.data.model.profile.LoginResponse
import com.example.onlineshopproject.data.model.profile.ProfileModel
import com.example.onlineshopproject.data.remote.BaseApiResponse
import com.example.onlineshopproject.data.remote.HomeApiInterface
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.data.remote.ProfileApiInterface
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api : HomeApiInterface
) : BaseApiResponse() {
    suspend fun getSliders():NetworkResult<List<SliderItem>> =
        safeApiCall {
            api.getSliders()
        }

    suspend fun getSuggested():NetworkResult<List<ProductItem>> =
        safeApiCall {
            api.getSuggested()
        }

    suspend fun getCenterBanners():NetworkResult<List<SliderItem>> =
        safeApiCall {
            api.getCenterBanners()
        }

    suspend fun getSpecialProducts():NetworkResult<List<ProductItem>> =
        safeApiCall {
            api.getSpecialProducts()
        }

}