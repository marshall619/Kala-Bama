package com.example.onlineshopproject.ui.Screens.productDetail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.data.model.poductDetail.ProductDetail
import com.example.onlineshopproject.ui.theme.Gold
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.semiDarkText
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.grayAlpha
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.h4

@Composable
fun StarWithText(item : ProductDetail){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.medium)
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "",
            modifier = Modifier.size(15.dp),
            tint = MaterialTheme.colors.Gold
        )
        Text(
            text = digitByLocate(item.star.toString()),
            color = MaterialTheme.colors.semiDarkText,
            style = Typography.h4,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
        Text(
            text = digitByLocate("(${item.starCount})"),
            color = MaterialTheme.colors.grayAlpha,
            style = Typography.h4,
            modifier = Modifier.padding(end = LocalSpacing.current.small)
        )

        Icon(
            painter = painterResource(id = R.drawable.dot),
            contentDescription = "",
            tint = MaterialTheme.colors.grayAlpha,
            modifier = Modifier
                .size(7.dp)
                .padding(horizontal = 1.dp)
        )

        Text(
            text = digitByLocate("${item.commentCount} ${stringResource(R.string.user_comments)}"),
            style = Typography.h4,
            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
        )

        Icon(
            painter = painterResource(id = R.drawable.dot),
            contentDescription = "",
            tint = MaterialTheme.colors.grayAlpha,
            modifier = Modifier
                .size(7.dp)
                .padding(horizontal = 1.dp)
        )
        Text(
            text = digitByLocate("${item.viewCount} ${stringResource(R.string.view)}"),
            style = Typography.h4,
            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
        )
    }
}