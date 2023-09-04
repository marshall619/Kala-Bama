package com.example.onlineshopproject.ui.Screens.basket

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.h4
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.DigitHelper

@Composable
fun AddressBottomBar(addressText: String, payablePrice: Long, onClick: () -> Unit) {
    val buttonColor = if (addressText == "") Color(0xff73C686) else Color(0xff25AC9E)
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(LocalSpacing.current.semiSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clip(LocalShape.current.extraSmall)
                .background(buttonColor)
                .clickable {
                    if (addressText != "") {
                        onClick()
                    } else {
                        Toast
                            .makeText(context, "لطفا آدرس خود را وارد کنید.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .padding(LocalSpacing.current.small)
                .padding(horizontal = LocalSpacing.current.small),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tick),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))
                Text(
                    text = "ادامه و پرداخت مبلغ",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "مبلغ قابل پرداخت  :",
                style = Typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.unSelectedBottomBar
            )

            Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = DigitHelper.digitByLocate(" ${DigitHelper.digitBySeparator(payablePrice.toString())}"),
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
    }
}