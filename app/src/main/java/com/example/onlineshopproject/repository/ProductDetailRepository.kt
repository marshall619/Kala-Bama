package com.example.onlineshopproject.repository

import com.example.onlineshopproject.data.model.poductDetail.NewComment
import com.example.onlineshopproject.data.model.poductDetail.ProductDetail
import com.example.onlineshopproject.data.remote.BaseApiResponse
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.data.remote.ProductDetailApiInterface
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(
    private val api : ProductDetailApiInterface
) : BaseApiResponse() {

    suspend fun getProductById(productId : String):NetworkResult<ProductDetail> =
        safeApiCall {
            api.getProductById(productId)
        }


    suspend fun setNewComment(newComment: NewComment ):NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }

}