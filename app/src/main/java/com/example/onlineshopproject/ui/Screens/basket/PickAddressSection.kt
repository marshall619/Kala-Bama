package com.example.onlineshopproject.ui.Screens.basket


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.data.model.checkOut.OrderDetail
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.backBackground
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.util.Constants.USER_PHONE
import com.example.onlineshopproject.util.Constants.USER_TOKEN
import com.example.onlineshopproject.viewModel.BasketViewModel
import com.example.onlineshopproject.viewModel.CheckOutViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PickAddressSection(navController: NavController , viewModel : CheckOutViewModel = hiltViewModel(),BasketViewModel: BasketViewModel = hiltViewModel()) {

    var addressText by remember { mutableStateOf("") }
    var needToPayMore by remember { mutableStateOf(false) }
    var payablePrice by remember { mutableStateOf(0L) }
    val cartDetails by BasketViewModel.cartDetails.collectAsState()
    val allItems by BasketViewModel.cartItems.collectAsState(emptyList())

    var orderId by remember { mutableStateOf("") }

    LaunchedEffect(Dispatchers.Main){
        viewModel.orderResponse.collectLatest { orderResult ->
            when (orderResult) {
                is NetworkResult.Success -> {
                    orderId = orderResult.data ?: ""
                    navController.navigate(Screens.ConfirmPurchase.withArgs(orderId , payablePrice))
                }

                is NetworkResult.Error -> {
                    Log.e("6191", "orderResult Api Error is :${orderResult.message}")
                }

                is NetworkResult.Loading -> {}
            }
        }
    }

    Scaffold(bottomBar = {
        AddressBottomBar(addressText = addressText,payablePrice = payablePrice){
            viewModel.setNewOrder(
                OrderDetail(
                    orderAddress = addressText,
                    orderProducts = allItems,
                    orderTotalDiscount = cartDetails.totalDiscount,
                    orderTotalPrice = payablePrice,
                    orderUserName = "addressName",
                    orderUserPhone = USER_PHONE,
                    token = USER_TOKEN
                )
            )
        }
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp, top = LocalSpacing.current.small)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = LocalSpacing.current.medium,
                                vertical = LocalSpacing.current.small
                            ),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(26.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.backBackground)
                                .clickable {
                                    navController.navigate(Screens.Basket.route) {
                                        popUpTo(Screens.Address.route) {
                                            inclusive = true
                                        }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "close",
                                modifier = Modifier.size(20.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
                item {
                    Text(
                        text = "انتخاب آدرس و شیوه ارسال",
                        style = Typography.h2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalSpacing.current.medium),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
                }
                item {
                    PickAddressPlace(address = { addressText = it }, selectedPost = { needToPayMore = it == "post" })
                }
                item { Spacer(modifier = Modifier.height(LocalSpacing.current.medium)) }
                item { AddressSummery(needToPayMore = needToPayMore){payablePrice = it} }
                item { Spacer(modifier = Modifier.height(LocalSpacing.current.small)) }
            }
        }
    }


}