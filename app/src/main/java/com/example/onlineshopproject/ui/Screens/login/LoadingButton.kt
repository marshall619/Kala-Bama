package com.example.onlineshopproject.ui.Screens.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.ui.components.Loading3Dots
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.mainColor

@Composable
fun LoadingButton(){
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.mainColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(
                start = LocalSpacing.current.semiLarge,
                end = LocalSpacing.current.semiLarge,
                bottom = LocalSpacing.current.medium
            ),
        shape = LocalShape.current.small
    ) {
        Loading3Dots(isDark = false)
    }
}