package com.example.onlineshopproject.ui.Screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h4
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.util.DigitHelper.digitBySeparator
import com.example.onlineshopproject.viewModel.BasketViewModel

@Composable
fun BasketSummery(viewModel: BasketViewModel = hiltViewModel()) {
    val cartDetails by viewModel.cartDetails.collectAsState()


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.biggerMedium),
        elevation = 4.dp,
        shape = LocalShape.current.small
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.biggerMedium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "مجموع سبدخرید :",
                    style = Typography.h4,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.unSelectedBottomBar
                )

                Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = digitByLocate(" ${digitBySeparator(cartDetails.totalPrice.toString())}"),
                        style = Typography.h4,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(horizontal = LocalSpacing.current.extraSmall),
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(LocalSpacing.current.small))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "تخفیف :",
                    style = Typography.h4,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.unSelectedBottomBar
                )

                Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = digitByLocate(" ${digitBySeparator(cartDetails.totalDiscount.toString())}"),
                        style = Typography.h4,
                        color = MaterialTheme.colors.mainColor,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(horizontal = LocalSpacing.current.extraSmall),
                        tint = MaterialTheme.colors.mainColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(LocalSpacing.current.small))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color(0x12000000))
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.small))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "جمع کل :",
                    style = Typography.h4,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.unSelectedBottomBar
                )

                Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = digitByLocate(" ${digitBySeparator(cartDetails.payablePrice.toString())}"),
                        style = Typography.h4,
                        color = MaterialTheme.colors.mainColor,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.toman),
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(horizontal = LocalSpacing.current.extraSmall),
                        tint = MaterialTheme.colors.mainColor
                    )
                }
            }
        }
    }
}