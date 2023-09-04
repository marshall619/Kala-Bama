package com.example.onlineshopproject.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.navigation.NavigationBarScreen
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.navigation.SetupNavGraph
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.util.Constants.PRE_ROUTE
import com.example.onlineshopproject.util.Constants.USER_EMAIL
import com.example.onlineshopproject.util.Constants.USER_FAMILY
import com.example.onlineshopproject.util.Constants.USER_ID
import com.example.onlineshopproject.util.Constants.USER_ID_CODE
import com.example.onlineshopproject.util.Constants.USER_NAME
import com.example.onlineshopproject.util.Constants.USER_PASSWORD
import com.example.onlineshopproject.util.Constants.USER_PHONE
import com.example.onlineshopproject.util.Constants.USER_PHONE2
import com.example.onlineshopproject.util.Constants.USER_TOKEN
import com.example.onlineshopproject.viewModel.DataStoreViewModel
import com.example.onlineshopproject.viewModel.ProfileViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppConfig(
    navHostController: NavHostController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {
    //Configs
    ChangeTopBarColor(navController = navHostController)
    RefreshToken()
    //Ui
    StartAppUi(navHostController)
}

@Composable
fun RefreshToken(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {
    getDataVariables(dataStoreViewModel)

    profileViewModel.refreshToken(USER_PHONE, USER_PASSWORD)
    LaunchedEffect(Dispatchers.Main) {
        profileViewModel.profileResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {
                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {
                            dataStoreViewModel.saveUserTOKEN(user.token)
                            dataStoreViewModel.saveUserID(user.id)
                            dataStoreViewModel.saveUserPHONE(user.phone)
                            dataStoreViewModel.saveUserPASSWORD(USER_PASSWORD)

                            getDataVariables(dataStoreViewModel)
                        }

                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun ChangeTopBarColor(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()
    val mainColor = MaterialTheme.colors.mainColor
    when (navBackStackEntry?.destination?.route) {
        Screens.Splash.route -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color.White
                )
            }
        }

        Screens.Profile.route -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = mainColor
                )
            }
        }

        else -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color.White
                )
            }
        }
    }
}

private fun getDataVariables(dataStore: DataStoreViewModel) {
    USER_PASSWORD = dataStore.getUserPASSWORD().toString()
    USER_ID = dataStore.getUserID().toString()
    USER_PHONE = dataStore.getUserPHONE().toString()
    USER_TOKEN = dataStore.getUserTOKEN().toString()
    USER_PHONE2 = dataStore.getUSER_PHONE_KEY2()
    USER_ID_CODE = dataStore.getUSER_IDCODE()
    USER_EMAIL = dataStore.getUSER_EMAIL()
    USER_FAMILY = dataStore.getUSER_FAMILY()
    USER_NAME = dataStore.getUserName()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun StartAppUi(navHostController: NavHostController) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(bottomBar = {
            NavigationBarScreen(
                navController = navHostController,
                onItemClick = {
                    navHostController.navigate(it.route) {
                        if (PRE_ROUTE != Screens.Home.route) {
                            popUpTo(PRE_ROUTE) {
                                inclusive = true
                            }
                        }
                    }
                })
        }) {
            //show screens with navController & the screen is Splash for start
            SetupNavGraph(navHostController = navHostController)
        }
    }
}
