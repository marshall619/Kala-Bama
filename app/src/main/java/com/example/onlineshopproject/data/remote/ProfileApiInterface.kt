package com.example.onlineshopproject.data.remote

import com.example.onlineshopproject.data.model.ResponseResult
import com.example.onlineshopproject.data.model.profile.LoginResponse
import com.example.onlineshopproject.data.model.profile.ProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileApiInterface {

    @POST("v1/login")
    suspend fun login(
        @Body profileModel: ProfileModel
    ) : Response<ResponseResult<LoginResponse>>

}