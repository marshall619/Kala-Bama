package com.example.onlineshopproject.ui.Screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.h4
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.Constants.USER_NAME
import com.example.onlineshopproject.util.Constants.USER_PHONE

@Composable
fun ProfileSection(navController: NavController) {

    var userName = if (USER_NAME != "") USER_NAME else "نام کاربری"

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.mainColor)
                    .height(340.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(210.dp)
                        .clip(CircleShape)
                        .background(Color(0xff2EC8C8)), contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                            .clip(CircleShape)
                            .background(Color(0xff5ED2D2)), contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape)
                                .background(Color(0xff85E0E1)), contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icon),
                                contentDescription = null,
                                modifier = Modifier.size(120.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
                Text(
                    text = userName,
                    style = Typography.h2,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
                Text(
                    text = USER_PHONE,
                    style = Typography.h4,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }

        item { InformationCard(navController) }
    }
}