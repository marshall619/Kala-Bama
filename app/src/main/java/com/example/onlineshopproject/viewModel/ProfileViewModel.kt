package com.example.onlineshopproject.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopproject.data.model.profile.LoginResponse
import com.example.onlineshopproject.data.model.profile.ProfileModel
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.repository.ProfileRepository
import com.example.onlineshopproject.ui.Screens.login.ProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel(){

    var screenState by mutableStateOf(ProfileScreenState.LOGIN_STATE)
    var phoneTextField by mutableStateOf("")
    var passwordTextField by mutableStateOf("")
    var loading by mutableStateOf(false)

    val profileResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())

    fun login(){
        viewModelScope.launch(Dispatchers.IO){
            loading = true
            profileResponse.emit(repository.login(ProfileModel(phoneTextField , passwordTextField)))
        }
    }

    fun refreshToken(phone : String , password : String){
        viewModelScope.launch{
            loading = true
            profileResponse.emit(repository.login(ProfileModel(phone , password)))
        }
    }
}