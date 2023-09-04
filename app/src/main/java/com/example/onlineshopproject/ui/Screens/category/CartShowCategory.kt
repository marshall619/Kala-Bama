package com.example.onlineshopproject.ui.Screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h3

@Composable
fun CartShowCategory(text: String, color: Color, onClickListener: () -> Unit) {
    Card(
        shape = LocalShape.current.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                horizontal = LocalSpacing.current.semiSmall,
                vertical = LocalSpacing.current.small
            ),
        elevation = 10.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(color)
                .clickable {
                    onClickListener()
                },
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp)
                    .clip(LocalShape.current.medium)
                    .background(Color.White),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = text,
                    style = Typography.h3,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(
                        vertical = LocalSpacing.current.biggerSmall,
                        horizontal = LocalSpacing.current.medium
                    )
                )
            }
        }

    }
}