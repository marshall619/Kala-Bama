package com.example.onlineshopproject.ui.Screens.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.h5
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.util.DigitHelper
import com.example.onlineshopproject.util.DigitHelper.applyDiscount
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.util.DigitHelper.digitBySeparator

@Composable
fun ProductButton(price: Long, haveDiscount: Boolean = false, discount: Int, onClick: () -> Unit) {
    if (!haveDiscount) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = LocalSpacing.current.medium)
                .clip(RoundedCornerShape(68.dp))
                .background(MaterialTheme.colors.mainColor)
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = digitByLocate(
                        " خرید با تخفیف | ${
                            digitBySeparator(
                                applyDiscount(
                                    price,
                                    discount
                                ).toString()
                            )
                        }"
                    ),
                    style = Typography.h3,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))
                Icon(
                    painter = painterResource(id = R.drawable.toman),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(horizontal = LocalSpacing.current.extraSmall),
                    tint = Color.White
                )
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = LocalSpacing.current.medium)
                .clip(RoundedCornerShape(68.dp))
                .background(Color.White)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.mainColor,
                    shape = RoundedCornerShape(68.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = digitByLocate(" خرید | ${digitBySeparator(price.toString())}"),
                    style = Typography.h3,
                    color = MaterialTheme.colors.mainColor,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))
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