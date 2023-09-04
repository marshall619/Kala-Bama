package com.example.onlineshopproject.ui.Screens.productDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.poductDetail.Comment
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.DigitHelper.digitByLocate

@Composable
fun UserComments(commentCount: Int , items : List<Comment>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "نظر کاربران",
                style = Typography.h2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = LocalSpacing.current.small)
            )
        }
        Spacer(modifier = Modifier.height(LocalSpacing.current.small))
        LazyRow(modifier = Modifier.fillMaxWidth()){
            items(items){
                CommentCart(item = it)
            }
        }
    }
}

