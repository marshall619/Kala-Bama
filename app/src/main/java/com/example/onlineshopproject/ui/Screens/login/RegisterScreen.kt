package com.example.onlineshopproject.ui.Screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.digikalatestononline.ui.screens.profile.MyButton
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.darkText
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.selectedBottomBar
import com.example.onlineshopproject.util.Constants.USER_PHONE
import com.example.onlineshopproject.util.Constants.USER_TOKEN
import com.example.onlineshopproject.util.InputValidation.isValidPassword
import com.example.onlineshopproject.viewModel.DataStoreViewModel
import com.example.onlineshopproject.viewModel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel(),
    navController: NavController,
) {
    val context = LocalContext.current

    LaunchedEffect(Dispatchers.Main) {
        viewModel.profileResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {
                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {
                            dataStore.saveUserTOKEN(user.token)
                            dataStore.saveUserID(user.id)
                            dataStore.saveUserPHONE(user.phone)
                            USER_PHONE = user.phone
                            USER_TOKEN = user.token
                            dataStore.saveUserPASSWORD(viewModel.passwordTextField)

                            navController.navigate(Screens.Home.route){
                                popUpTo(Screens.Login.route){
                                    inclusive = true
                                }
                            }
                        }
                    }
                    Toast.makeText(
                        context,
                        loginResponse.message,
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("6191", "loginResponse Api Error is :${loginResponse.message}")
                    viewModel.loading = false
                }

                is NetworkResult.Loading -> {
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                ProfileScreenState.LOGIN_STATE
                navController.navigate(Screens.Login.route)
            }) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .padding(LocalSpacing.current.small),
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.set_password_text),
                modifier = Modifier.padding(
                    horizontal = LocalSpacing.current.semiLarge
                ),
                style = Typography.h6,
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold
            )

            MyEditText(
                value = viewModel.phoneTextField,
                placeholder = stringResource(id = R.string.phone_and_email),
                onValueChange = {

                },
                isLrt = true
            )

            MyEditText(
                value = viewModel.passwordTextField,
                placeholder = stringResource(id = R.string.set_password_text),
                onValueChange = {
                    viewModel.passwordTextField = it
                },
                isLrt = true
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
            if (viewModel.loading) {
                LoadingButton()
            } else {
                MyButton(text = stringResource(id = R.string.kalabama_entry)) {
                    if (isValidPassword(viewModel.passwordTextField)) {
                        viewModel.login()
                    } else {
                        Toast.makeText(
                            context,
                            context.resources.getText(R.string.password_format_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


}