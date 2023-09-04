package com.example.onlineshopproject.ui.Screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h2

@Composable
fun GoToAddressSection(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.medium)
            .padding(bottom = LocalSpacing.current.small)
            .clip(LocalShape.current.biggerSmall)
            .background(Color(0xff25AC9E))
            .clickable {
                onClick()
            }
            .padding(LocalSpacing.current.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .padding(start = LocalSpacing.current.small)
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(0xff51BDB1)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.k1),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }
        Text(
            text = "انتخاب آدرس و شیوه ارسال",
            style = Typography.h2,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}