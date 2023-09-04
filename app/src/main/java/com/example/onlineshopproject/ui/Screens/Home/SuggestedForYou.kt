package com.example.onlineshopproject.ui.Screens.Home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.DarkCyan
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.body2
import com.example.onlineshopproject.ui.theme.darkText
import com.example.onlineshopproject.ui.theme.extraSmall
import com.example.onlineshopproject.ui.theme.gradientBackGroundColor
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.ui.theme.semiDarkText
import com.example.onlineshopproject.ui.theme.specialTextColor
import com.example.onlineshopproject.util.Constants
import com.example.onlineshopproject.util.DigitHelper.applyDiscount
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.util.DigitHelper.digitBySeparator
import com.example.onlineshopproject.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestedForYou(navController: NavController,viewModel: HomeViewModel = hiltViewModel()) {

    var suggestedList by remember { mutableStateOf(emptyList<ProductItem>()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        viewModel.suggestedList.collectLatest { suggestedResult ->
            when (suggestedResult) {
                is NetworkResult.Success -> {
                    suggestedResult.data?.let {
                        suggestedList = it
                    }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("6191", "suggested api ${suggestedResult.message}")
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
            .background(MaterialTheme.colors.gradientBackGroundColor)
            .padding(
                horizontal = LocalSpacing.current.biggerSmall,
                vertical = LocalSpacing.current.biggerSmall
            )
    ) {
        Text(
            text = stringResource(id = R.string.suggested_for_you),
            style = Typography.h2,
            fontWeight = FontWeight.Bold
        )
        FlowRow(maxItemsInEachRow = 2 , modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            suggestedList.forEach {
                ItemShow(item = it , navController)
            }
        }
    }
}

@Composable
private fun ItemShow(item: ProductItem,navController: NavController) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .padding(
                horizontal = LocalSpacing.current.semiSmall,
                vertical = LocalSpacing.current.semiLarge,
            )
            .clickable {
                Constants.PRE_SCREEN = Screens.Home.route
                navController.navigate(Screens.ProductDetail.route + "?productId=${item._id}")
            },
        shape = LocalShape.current.small,
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.small)
        ) {


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LocalSpacing.current.semiSmall)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.image),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.FillBounds
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LocalSpacing.current.small)
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(48.dp)
                        .padding(horizontal = LocalSpacing.current.small),
                    style = Typography.h6,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.darkText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.small),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.in_stock),
                        contentDescription = "",
                        modifier = Modifier
                            .size(22.dp)
                            .padding(2.dp),
                        tint = MaterialTheme.colors.DarkCyan
                    )

                    Text(
                        text = "موجود در انبار",
                        style = Typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.semiDarkText
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ){

                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(24.dp)
                        .background(
                            color = MaterialTheme.colors.mainColor,
                            shape = CircleShape
                        )
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "${digitByLocate(item.discountPercent.toString())}%",
                        color = Color.White,
                        style = Typography.h6,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Column {

                    Row {
                        Text(
                            text = digitByLocate(digitBySeparator(item.price.toString())),
                            style = Typography.body2,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.toman),
                            contentDescription = "",
                            modifier = Modifier
                                .size(LocalSpacing.current.semiLarge)
                                .padding(horizontal = LocalSpacing.current.extraSmall),
                        )

                    }

                    Text(
                        text = digitByLocate(digitBySeparator(applyDiscount(item.price , item.discountPercent).toString())),
                        color = Color.LightGray,
                        style = Typography.body2,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }
        }
    }
}