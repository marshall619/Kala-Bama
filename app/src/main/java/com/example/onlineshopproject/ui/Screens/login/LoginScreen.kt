package com.example.onlineshopproject.ui.Screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikalatestononline.ui.screens.profile.MyButton
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.darkText
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.searchBarBg
import com.example.onlineshopproject.ui.theme.semiDarkText
import com.example.onlineshopproject.util.InputValidation.isValidEmail
import com.example.onlineshopproject.util.InputValidation.isValidPhoneNumber
import com.example.onlineshopproject.viewModel.ProfileViewModel

@Composable
fun LoginScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item { Spacer(modifier = Modifier.height(LocalSpacing.current.large)) }
        item {
            Image(
                modifier = Modifier
                    .size(160.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
            )
        }
        item { Spacer(modifier = Modifier.height(LocalSpacing.current.large)) }
        item {
            Text(
                modifier = Modifier.padding(
                    horizontal = LocalSpacing.current.semiLarge
                ),
                style = Typography.h6,
                text = stringResource(id = R.string.loginTxt),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            MyEditText(
                value = viewModel.phoneTextField,
                placeholder = stringResource(id = R.string.phone_and_email),
                onValueChange = {
                    viewModel.phoneTextField = it
                },
                isLrt = true
            )
        }
        item {
            MyButton(text = stringResource(id = R.string.kalabama_entry)) {
                if (isValidPhoneNumber(viewModel.phoneTextField) || isValidEmail(viewModel.phoneTextField)) {
                    viewModel.screenState = ProfileScreenState.REGISTER_STATE
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.login_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        item {
            Divider(
                color = MaterialTheme.colors.searchBarBg,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(top = LocalSpacing.current.medium)
            )
        }
        item {
            TermsAndRulesText(
                fullText = stringResource(id = R.string.terms_and_rules_full),
                underlinedText = listOf(
                    stringResource(id = R.string.terms_and_rules),
                    stringResource(id = R.string.privacy_and_rules)
                ),
                textColor = MaterialTheme.colors.semiDarkText,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}