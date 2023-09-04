package com.example.onlineshopproject.ui.Screens.basket

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.checkOut.ConfirmPurchase
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h5
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.util.Constants
import com.example.onlineshopproject.util.DigitHelper
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.util.DigitHelper.digitBySeparator
import com.example.onlineshopproject.util.ZarinpalPurchase
import com.example.onlineshopproject.viewModel.BasketViewModel
import com.example.onlineshopproject.viewModel.CheckOutViewModel

@Composable
fun ConfirmPurchaseScreen(
    navController: NavHostController,
    orderId : String,
    orderPrice : String,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckOutViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val activity = context as Activity

    var orderState by remember { mutableStateOf(context.getString(R.string.waiting_for_purchase)) }

    LaunchedEffect(true ){
        ZarinpalPurchase.fakePurchase(
            activity,
            orderPrice.toLong(),
            "خرید تستی "
        ) { transactionID ->
            orderState = context.getString(R.string.purchase_is_ok)
            basketViewModel.deleteAllItems()
            checkoutViewModel.confirmPurchase(
                ConfirmPurchase(
                    token = Constants.USER_TOKEN,
                    transactionId = transactionID,
                    orderId = orderId
                )
            )
            Log.e("3636", "Transaction ID : $transactionID")
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                LocalSpacing.current.medium,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.final_price),
                style = Typography.h5
            )

            Text(
                text = digitByLocate(digitBySeparator(orderPrice)),
                style = Typography.h5
            )
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_status),
                style = Typography.h5
            )

            Text(
                text = orderState,
                style = Typography.h5
            )
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_code),
                style = Typography.h5
            )

            Text(
                text = orderId,
                style = Typography.h5
            )
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        Button(
            onClick = {
                navController.navigate(Screens.Home.route){
                    popUpTo(Screens.Home.route) {
                        inclusive = true
                    }
                }
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.mainColor),
            shape = LocalShape.current.small,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        LocalSpacing.current.small,
                    ),
                text = stringResource(id = R.string.return_to_home_page),
                color = MaterialTheme.colors.mainColor,
                style = Typography.h5,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}