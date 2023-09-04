package com.example.onlineshopproject.ui.Screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.R
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.viewModel.BasketViewModel

@Composable
fun BasketSection(navController: NavController) {
    Basket(navController = navController)
}

@Composable
private fun Basket(viewModel: BasketViewModel = hiltViewModel(),navController: NavController) {
    val cartItem by viewModel.cartItems.collectAsState(emptyList())

    if (cartItem.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp, top = LocalSpacing.current.small)
        ) {
            item {
                Text(
                    text = "محتویات سبد خرید",
                    style = Typography.h2,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = LocalSpacing.current.medium),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            }
            items(cartItem) {
                CartItemCard(item = it)
            }
            item {BasketSummery()}
            item {GoToAddressSection{navController.navigate(Screens.Address.route)} }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.empty_cart), contentDescription = null)
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "سبد شما خالی است!",
                style = Typography.h3,
                color = MaterialTheme.colors.unSelectedBottomBar
            )
        }
    }
}