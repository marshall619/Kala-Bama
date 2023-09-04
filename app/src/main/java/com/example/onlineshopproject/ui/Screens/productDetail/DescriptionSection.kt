package com.example.onlineshopproject.ui.Screens.productDetail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar

@Composable
fun DescriptionSection(description: String) {
    var isOpen by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isOpen) 90f else 270f,
        animationSpec = tween(delayMillis = 400)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.medium)
            .animateContentSize { initialValue, targetValue ->

            },
        shape = LocalShape.current.small,
        elevation = 6.dp,

    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        isOpen = !isOpen
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "معرفی اجمالی محصول",
                    style = Typography.h2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small)
                )
                Icon(
                    painter = painterResource(id = R.drawable.leftarrow),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(rotation)
                        .size(15.dp),
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(LocalSpacing.current.small))
            if(isOpen){
                Text(
                    text = description,
                    style = Typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    color = MaterialTheme.colors.unSelectedBottomBar,
                )
            }else{
                Text(
                    text = description,
                    style = Typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                    color = MaterialTheme.colors.unSelectedBottomBar,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 4
                )
            }

        }
    }


}