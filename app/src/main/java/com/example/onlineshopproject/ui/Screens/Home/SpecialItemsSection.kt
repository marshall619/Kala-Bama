package com.example.onlineshopproject.ui.Screens.Home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h4
import com.example.onlineshopproject.ui.theme.specialBackGround
import com.example.onlineshopproject.ui.theme.specialTextColor
import com.example.onlineshopproject.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SpecialItemsSection(navController: NavController,viewModel: HomeViewModel = hiltViewModel()) {
    var specialList by remember { mutableStateOf(emptyList<ProductItem>()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        viewModel.specialItemList.collectLatest { specialItemResult ->
            when (specialItemResult) {
                is NetworkResult.Success -> {
                    specialItemResult.data?.let {
                        specialList = it
                    }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("6191", "specialItems api ${specialItemResult.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.specialBackGround)
            .padding(horizontal = LocalSpacing.current.small)
    ) {
        Text(
            text = stringResource(id = R.string.specialTop),
            style = Typography.h2,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.specialTextColor,
            modifier = Modifier.padding(vertical = LocalSpacing.current.semiMedium)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(specialList) { item ->
                SpecialItemCard(item , navController)
            }
        }
    }

}