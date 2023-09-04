package com.example.onlineshopproject.util

import androidx.core.text.isDigitsOnly

object InputValidation {
    fun isValidPhoneNumber(input : String) : Boolean{
        return input.isNotEmpty()
                && input.isNotBlank()
                && input.startsWith("09")
                && input.isDigitsOnly()
                && input.length == 11
    }

    fun isValidEmail(input: String) : Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    fun isValidPassword(input: String) : Boolean{
        return input.isNotEmpty() && input.isNotBlank() && input.length > 6
    }
}