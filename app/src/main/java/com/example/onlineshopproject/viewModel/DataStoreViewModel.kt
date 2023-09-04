package com.example.onlineshopproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopproject.data.dataStore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel(){

    companion object {
        const val USER_TOKEN_KEY = "USER_TOKEN_KEY"
        const val USER_ID_KEY = "USER_ID_KEY"
        const val USER_PASSWORD_KEY = "USER_PASSWORD_KEY"
        const val USER_PHONE_KEY = "USER_PHONE_KEY"
        const val USER_NAME_KEY = "USER_NAME_KEY"
        const val USER_FAMILY_KEY = "USER_FAMILY_KEY"
        const val USER_EMAIL_KEY = "USER_EMAIL_KEY"
        const val USER_IDCODE_KEY = "USER_IDCODE_KEY"
        const val USER_PHONE_KEY2 = "USER_PHONE_KEY2"
    }


    fun saveUserTOKEN(value: String) {
        viewModelScope.launch {
            repository.putString(USER_TOKEN_KEY, value)
        }
    }

    fun getUserTOKEN(): String? = runBlocking {
        repository.getString(USER_TOKEN_KEY)
    }

    fun saveUserID(value: String) {
        viewModelScope.launch {
            repository.putString(USER_ID_KEY, value)
        }
    }

    fun getUserID(): String? = runBlocking {
        repository.getString(USER_ID_KEY)
    }

    fun saveUserPASSWORD(value: String) {
        viewModelScope.launch {
            repository.putString(USER_PASSWORD_KEY, value)
        }
    }

    fun getUserPASSWORD(): String? = runBlocking {
        repository.getString(USER_PASSWORD_KEY)
    }

    fun saveUserPHONE(value: String) {
        viewModelScope.launch {
            repository.putString(USER_PHONE_KEY, value)
        }
    }

    fun getUserPHONE(): String ? = runBlocking {
        repository.getString(USER_PHONE_KEY)
    }
//related to profile screen ........................................................
    fun saveUserName(value: String) {
        viewModelScope.launch {
            repository.putString(USER_NAME_KEY, value)
        }
    }

    fun getUserName(): String  = runBlocking {
        repository.getString(USER_NAME_KEY) ?: ""
    }

    fun saveUSER_FAMILY(value: String) {
        viewModelScope.launch {
            repository.putString(USER_FAMILY_KEY, value)
        }
    }

    fun getUSER_FAMILY(): String  = runBlocking {
        repository.getString(USER_FAMILY_KEY) ?: ""
    }

    fun saveUSER_EMAIL(value: String) {
        viewModelScope.launch {
            repository.putString(USER_EMAIL_KEY, value)
        }
    }

    fun getUSER_EMAIL(): String  = runBlocking {
        repository.getString(USER_EMAIL_KEY) ?: ""
    }

    fun saveUSER_IDCODE(value: String) {
        viewModelScope.launch {
            repository.putString(USER_IDCODE_KEY, value)
        }
    }

    fun getUSER_IDCODE(): String  = runBlocking {
        repository.getString(USER_IDCODE_KEY) ?: ""
    }

    fun saveUSER_PHONE_KEY2(value: String) {
        viewModelScope.launch {
            repository.putString(USER_PHONE_KEY2, value)
        }
    }

    fun getUSER_PHONE_KEY2(): String  = runBlocking {
        repository.getString(USER_PHONE_KEY2) ?: ""
    }
}