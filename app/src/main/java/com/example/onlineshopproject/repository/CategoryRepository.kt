package com.example.onlineshopproject.repository

import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.model.home.SliderItem
import com.example.onlineshopproject.data.model.profile.LoginResponse
import com.example.onlineshopproject.data.model.profile.ProfileModel
import com.example.onlineshopproject.data.remote.BaseApiResponse
import com.example.onlineshopproject.data.remote.CategoryApiInterface
import com.example.onlineshopproject.data.remote.HomeApiInterface
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.data.remote.ProfileApiInterface
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api : CategoryApiInterface
) : BaseApiResponse() {

    suspend fun getAmazingProducts():NetworkResult<List<ProductItem>> =
        safeApiCall {
            api.getAmazingProducts()
        }

    suspend fun getSuperMarketAmazingProducts():NetworkResult<List<ProductItem>> =
        safeApiCall {
            api.getSuperMarketAmazingProducts()
        }

    suspend fun getMostDiscountedProducts():NetworkResult<List<ProductItem>> =
        safeApiCall {
            api.getMostDiscountedProducts()
        }

    suspend fun getBestsellerProducts():NetworkResult<List<ProductItem>> =
        safeApiCall {
            api.getBestsellerProducts()
        }

    suspend fun getMostVisitedProducts():NetworkResult<List<ProductItem>> =
        safeApiCall {
            api.getMostVisitedProducts()
        }

    suspend fun getMostFavoriteProducts():NetworkResult<List<ProductItem>> =
        safeApiCall {
            api.getMostFavoriteProducts()
        }


}