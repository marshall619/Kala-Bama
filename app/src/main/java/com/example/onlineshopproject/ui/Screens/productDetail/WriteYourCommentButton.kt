package com.example.onlineshopproject.ui.Screens.productDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.commendWrite
import com.example.onlineshopproject.ui.theme.commentCartBackground
import com.example.onlineshopproject.ui.theme.h3

@Composable
fun WriteYourCommentButton(starSize: Dp = 40.dp, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.medium)
                .size(300.dp, 200.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = LocalSpacing.current.large,
                        bottomStart = LocalSpacing.current.large
                    )
                ),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.commentCartBackground)
                    .padding(
                        LocalSpacing.current.medium
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.staroutfill),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(starSize)
                            .padding(LocalSpacing.current.extraSmall)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.staroutfill),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(starSize)
                            .padding(LocalSpacing.current.extraSmall)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.staroutfill),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(starSize)
                            .padding(LocalSpacing.current.extraSmall)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.staroutfill),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(starSize)
                            .padding(LocalSpacing.current.extraSmall)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.staroutfill),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(starSize)
                            .padding(LocalSpacing.current.extraSmall)
                    )

                }
                Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(horizontal = LocalSpacing.current.large)
                        .clip(RoundedCornerShape(120.dp))
                        .background(MaterialTheme.colors.commentCartBackground)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colors.commendWrite,
                            shape = RoundedCornerShape(68.dp)
                        ).clickable {
                              onClick()
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "نوشتن",
                            style = Typography.h3,
                            color = MaterialTheme.colors.commendWrite
                        )
                        Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))
                        Icon(
                            painter = painterResource(id = R.drawable.write),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(horizontal = LocalSpacing.current.extraSmall),
                            tint = MaterialTheme.colors.commendWrite
                        )
                    }
                }
            }
        }
    }

}