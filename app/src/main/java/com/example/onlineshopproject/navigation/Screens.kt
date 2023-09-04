package com.example.onlineshopproject.navigation

sealed class Screens (val route : String) {
    object Splash : Screens("Splash_Screen")
    object Home : Screens("Home_Screen")
    object Login : Screens("Login_Screen")
    object Basket : Screens("Basket_Screen")
    object Profile : Screens("Profile_Screen")
    object WebView : Screens("WebView_Screen")
    object Category : Screens("Category_Screen")
    object Amazing : Screens("Amazing_Screen")
    object BestSells : Screens("BestSells_Screen")
    object MostDiscount : Screens("MostDiscount_Screen")
    object MostFavorite : Screens("MostFavorite_Screen")
    object MostVisited : Screens("MostVisited_Screen")
    object SuperMarket : Screens("SuperMarket_Screen")
    object ProductDetail : Screens("ProductDetail_Screen")
    object NewComment : Screens("NewComment_Screen")
    object Address : Screens("Address_Screen")
    object ConfirmPurchase : Screens("ConfirmPurchase_Screen")


    fun withArgs(vararg args : Any) : String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}