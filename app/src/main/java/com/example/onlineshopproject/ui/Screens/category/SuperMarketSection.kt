package com.example.onlineshopproject.ui.Screens.category

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.colorArray
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.viewModel.CategoryViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuperMarketSection(navController: NavController, viewModel : CategoryViewModel = hiltViewModel()){
    var superMarketItems by remember { mutableStateOf(emptyList<ProductItem>()) }
    var loading by remember { mutableStateOf(true) }
    val color = MaterialTheme.colors.colorArray[1]
    val text = "محصولات سوپرمارکتی"
    //call api
    viewModel.getSuperMarketItemsApiCall()

    LaunchedEffect(true) {
        viewModel.superMarketItems.collectLatest { superMarketItemsResult ->
            when (superMarketItemsResult) {
                is NetworkResult.Success -> {
                    superMarketItemsResult.data?.let {
                        superMarketItems = it
                    }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("6191", "superMarketItemsResult api ${superMarketItemsResult.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalSpacing.current.biggerSmall,
                vertical = LocalSpacing.current.biggerSmall
            )
    ) {
        item {
            Text(
                text = text,
                style = Typography.h2,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .height(2.dp)
                    .background(color)
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
        }
        item {
            FlowRow(
                maxItemsInEachRow = 2,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                superMarketItems.forEach {
                    ProductCategoryCart(item = it , color = color , navController,
                        Screens.SuperMarket.route)
                }
            }
        }
    }
}