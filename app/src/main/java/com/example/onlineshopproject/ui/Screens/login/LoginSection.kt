package com.example.onlineshopproject.ui.Screens.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.Screens.Home.HomeSection
import com.example.onlineshopproject.viewModel.DataStoreViewModel
import com.example.onlineshopproject.viewModel.ProfileViewModel

@Composable
fun LoginSection(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    when (viewModel.screenState) {
        ProfileScreenState.LOGIN_STATE -> {
            LoginScreen()
        }

        ProfileScreenState.HOME_STATE -> {
            navController.navigate(Screens.Home.route) {
                popUpTo(Screens.Login.route) {
                    inclusive = true
                }
            }
        }

        ProfileScreenState.REGISTER_STATE -> {
            RegisterScreen(navController = navController)
        }
    }

}