package com.example.onlineshopproject.ui.Screens.basket

import android.icu.text.UnicodeSet.SpanCondition
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.Screens.login.MyEditText
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h4
import com.example.onlineshopproject.ui.theme.trashColorBasket
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.util.DigitHelper.digitBySeparator

@Composable
fun PickAddressPlace(address: (String) -> Unit , selectedPost : (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf("post") }
    address(text)
    selectedPost(selectedOption)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.small),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = null,
                    modifier = Modifier.size(8.dp),
                    tint = Color(0xff008072)
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                Text(
                    text = "انتخاب آدرس",
                    style = Typography.h4,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle()) {
                            append(" (")
                        }
                        withStyle(style = SpanStyle(color = MaterialTheme.colors.trashColorBasket)) {
                            append("*")
                        }
                        withStyle(style = SpanStyle()) {
                            append(")")
                        }
                    },
                    style = Typography.h4,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.height(LocalSpacing.current.small))
            MyEditText(
                value = text,
                placeholder = "آدرس مورد نظر را در اینجا وارد کنید .",
                onValueChange = { text = it },
                editHeight = 200
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = null,
                    modifier = Modifier.size(8.dp),
                    tint = Color(0xff008072)
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                Text(
                    text = "روش ارسال",
                    style = Typography.h4,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == "post",
                    onClick = { selectedOption = "post" },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = MaterialTheme.colors.unSelectedBottomBar,
                        selectedColor = Color(0xff008072),
                    ),
                    modifier = Modifier.size(8.dp)
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.biggerSmall))
                Text(
                    text = "ارسال پستی پیشتاز(${digitByLocate(digitBySeparator(30000.toString()))}تومان) ",
                    style = Typography.h4,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.unSelectedBottomBar
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption == "tePox",
                    onClick = { selectedOption = "tePox" },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = MaterialTheme.colors.unSelectedBottomBar,
                        selectedColor = Color(0xff008072),
                    ),
                    modifier = Modifier.size(8.dp)
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.biggerSmall))
                Text(
                    text = "تیپاکس (کرایه در مقصد)",
                    style = Typography.h4,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.unSelectedBottomBar
                )
            }
        }
    }
}