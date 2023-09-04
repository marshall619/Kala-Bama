package com.example.onlineshopproject.ui.Screens.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.ui.theme.CursorColor
import com.example.onlineshopproject.ui.theme.DarkCyan
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.ui.theme.searchBarBg

@Composable
fun ProfileEditText(
    value: String,
    placeholder:String,
    editHeight : Int = 92,
    onValueChange: (String) -> Unit,
    isLrt : Boolean = false
){

    TextField(
        value = value,
        onValueChange = {onValueChange(it)},
        modifier = Modifier
            .fillMaxWidth()
            .height(editHeight.dp)
            .padding(
                start = LocalSpacing.current.semiLarge,
                end = LocalSpacing.current.semiLarge,
                top = LocalSpacing.current.medium,
                bottom = LocalSpacing.current.semiLarge),
        shape = LocalShape.current.large,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.searchBarBg,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor =  Color.Transparent,
            cursorColor =  MaterialTheme.colors.mainColor
        ),
        placeholder = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                Text(
                    text = placeholder,
                    style = Typography.h6,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    )
}