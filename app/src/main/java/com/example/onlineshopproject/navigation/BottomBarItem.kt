package com.example.onlineshopproject.navigation

import androidx.compose.ui.graphics.painter.Painter

data class BottomBarItem(
    val name : String,
    val route : String,
    val selectedItem : Painter,
    val deSelectedItem : Painter
)
