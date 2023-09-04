package com.example.onlineshopproject.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Colors.mainColor: Color
    @Composable
    get() = Color(0xff01B7BA)

val Colors.CursorColor : Color
    @Composable
    get() = Color(0xFF018577)

val Colors.Oranges: Color
    @Composable
    get() = Color(0xFFFF5722)

val Colors.Green : Color
    @Composable
    get() = Color(0xFF00A049)

val Colors.darkText: Color
    @Composable
    get() =  Color(0xFF414244)

val Colors.searchBarBg : Color
    @Composable
    get() =  Color(0xFFF1F0EE) 

val Colors.unSelectedBottomBar : Color
    @Composable
    get() =  Color(0xffa4a1a1)

val Colors.selectedBottomBar : Color
    @Composable
    get() =  Color(0xff43474c)

val Colors.semiDarkText: Color
    @Composable
    get() =  Color(0xFF5C5E61)

val Colors.DarkCyan: Color
    @Composable
    get() = Color(0xFF0fabc6)

val Colors.bottomBar: Color
    @Composable
    get() =  Color(0xFFFFFFFF) 

val Colors.endWhite: Color
    @Composable
    get() = Color(0xffF1F2ED)

val Colors.startWhite: Color
    @Composable
    get() = Color(0xFFFFFFFF)

val Colors.gradientBackGroundColor : Brush
    @Composable
    get() = Brush.verticalGradient(listOf(MaterialTheme.colors.startWhite, MaterialTheme.colors.endWhite))

val Colors.amber: Color
    @Composable
    get() =  Color(0xffFFBF00)

val Colors.specialBackGround: Color
    @Composable
    get() =  Color(0xffDCF8EA)

val Colors.specialTextColor: Color
    @Composable
    get() =  Color(0xff1BABCB)

val Colors.specialRed: Color
    @Composable
    get() =  Color(0xffE05C5C)

val Colors.specialCartColor: Color
    @Composable
    get() =  Color(0xffEDFBFC)

val Colors.backBackground: Color
    @Composable
    get() =  Color(0xffB7B7B7)

val Colors.grayAlpha: Color
    @Composable
    get() = Color(0xFFc1c2c6)

val Colors.commentCartBackground: Color
    @Composable
    get() = Color(0xFFF1F1EF)

val Colors.colorArray: List<Color>
    @Composable
    get() =  listOf(
        Color(0xffFFCC80),
        Color(0xffFFAB91),
        Color(0xffE7ED9B),
        Color(0xff81DEEA),
        Color(0xffCF94DA),
        Color(0xffF48FB1),
        Color(0xff7DCAC2)
    )

val Colors.Gold : Color
    @Composable
    get() = Color(0xFFf9bc01)

val Colors.commendRecommended : Color
    @Composable
    get() = Color(0xFF009996)

val Colors.commendWrite : Color
    @Composable
    get() = Color(0xFF438488)

val Colors.grayCategory: Color
    @Composable
    get() = Color(0xFFF1F0EE)

val Colors.closeIcon: Color
    @Composable
    get() = Color(0xFFE04F5F)

val Colors.trashColorBasket: Color
    @Composable
    get() = Color(0xFFE15C65)

val Colors.backgroundTrashBasket: Color
    @Composable
    get() = Color(0xFFFCEDED)

val Colors.backgroundAddBasket: Color
    @Composable
    get() = Color(0xFFF0FFF8)

val Colors.plusBasket: Color
    @Composable
    get() = Color(0xFF25ACA2)