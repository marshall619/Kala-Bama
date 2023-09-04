package com.example.onlineshopproject.ui.Screens.category

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.colorArray
import com.example.onlineshopproject.ui.theme.h1
import com.example.onlineshopproject.viewModel.CategoryViewModel

@Composable
fun CategorySection(navHostController: NavHostController) {
    Category(navHostController)
}

private data class TextWithRoute(
    val text: String,
    val route: String,
)

@Composable
private fun Category(navHostController: NavHostController) {

    val categoryItems = listOf(
        TextWithRoute(
            "محصولات شگفت انگیز",
            Screens.Amazing.route
        ),
        TextWithRoute(
            "محصولات سوپرمارکتی",
            Screens.SuperMarket.route
        ),
        TextWithRoute(
            "منتخب محصولات حراج و تخفیف",
            Screens.MostDiscount.route
        ),
        TextWithRoute(
            "پرفروش ترین محصولات",
            Screens.BestSells.route
        ),
        TextWithRoute(
            "پربازدیدترین محصولات",
            Screens.MostVisited.route
        ),
        TextWithRoute(
            "محبوب ترین محصولات",
            Screens.MostFavorite.route
        )
    )

    var colorArrayIndex = 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
            .padding(top = LocalSpacing.current.semiSmall)
            .padding(horizontal = LocalSpacing.current.small)
    ) {
        Text(text = "دسته بندی", style = Typography.h1, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(LocalSpacing.current.small))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            categoryItems.forEach { item ->
                CartShowCategory(
                    text = item.text,
                    MaterialTheme.colors.colorArray[colorArrayIndex]
                ) {
                    navHostController.navigate(item.route)
                }
                colorArrayIndex++
            }
        }
    }
}
