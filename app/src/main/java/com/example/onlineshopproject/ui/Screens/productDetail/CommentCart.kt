package com.example.onlineshopproject.ui.Screens.productDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.poductDetail.Comment
import com.example.onlineshopproject.ui.theme.Gold
import com.example.onlineshopproject.ui.theme.Green
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.closeIcon
import com.example.onlineshopproject.ui.theme.commendRecommended
import com.example.onlineshopproject.ui.theme.commentCartBackground
import com.example.onlineshopproject.ui.theme.grayAlpha
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.h4
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.semiDarkText
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.util.DigitHelper.gregorianToJalali


@Composable
fun CommentCart(item: Comment) {
    val context = LocalContext.current
    var iconSuggestion = R.drawable.check
    var colorSuggestion = MaterialTheme.colors.Green
    var textSuggestion = context.getString(R.string.good_comment)

    val dateParts = item.updatedAt.substringBefore("T").split("-")
    val year = dateParts[0].toInt()
    val month = dateParts[1].toInt()
    val day = dateParts[2].toInt()

    when (item.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.closefill
            colorSuggestion = MaterialTheme.colors.closeIcon
            textSuggestion = stringResource(R.string.bad_comment)

        }
        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colors.semiDarkText
            textSuggestion = context.getString(R.string.so_so_comment)

        }
        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.check
            colorSuggestion = MaterialTheme.colors.commendRecommended
            textSuggestion = context.getString(R.string.good_comment)

        }
    }
    
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
                )
        ) {
            Text(
                text = item.userName,
                style = Typography.h3,
                modifier = Modifier.padding(bottom = LocalSpacing.current.extraSmall)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp),
                    tint = MaterialTheme.colors.Gold
                )
                Text(
                    text = digitByLocate(item.star.toString()),
                    color = Color.Black,
                    style = Typography.h2,
                    modifier = Modifier.padding(horizontal = 2.dp)
                )

                Icon(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = "",
                    tint = MaterialTheme.colors.grayAlpha,
                    modifier = Modifier
                        .size(7.dp)
                        .padding(horizontal = 1.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = iconSuggestion),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp)
                        .size(20.dp),
                )
                Text(
                    text = textSuggestion,
                    style = Typography.h6,
                    modifier = Modifier.padding(bottom = LocalSpacing.current.extraSmall),
                    color = colorSuggestion
                )
            }
            Text(
                text = item.description,
                style = Typography.h4,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                color = MaterialTheme.colors.unSelectedBottomBar,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.medium),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = digitByLocate(gregorianToJalali(year, month, day)),
                color = MaterialTheme.colors.semiDarkText,
                style = Typography.h6,
            )
            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                Modifier
                    .size(20.dp)
                    .padding(horizontal = LocalSpacing.current.small),
                tint = MaterialTheme.colors.grayAlpha
            )
            Text(
                text = item.userName,
                color = MaterialTheme.colors.grayAlpha,
                style = Typography.h6
            )
        }
    }
}