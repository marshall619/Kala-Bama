package com.example.onlineshopproject.repository

import com.example.onlineshopproject.data.model.profile.LoginResponse
import com.example.onlineshopproject.data.model.profile.ProfileModel
import com.example.onlineshopproject.data.remote.BaseApiResponse
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.data.remote.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api : ProfileApiInterface
) : BaseApiResponse() {

    suspend fun login(profileModel: ProfileModel) : NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(profileModel)
        }

}