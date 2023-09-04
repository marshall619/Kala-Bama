package com.example.onlineshopproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.onlineshopproject.ui.Screens.Home.HomeSection
import com.example.onlineshopproject.ui.Screens.Home.WebPageScreenSection
import com.example.onlineshopproject.ui.Screens.Splash.SplashSection
import com.example.onlineshopproject.ui.Screens.basket.BasketSection
import com.example.onlineshopproject.ui.Screens.basket.ConfirmPurchaseScreen
import com.example.onlineshopproject.ui.Screens.basket.PickAddressSection
import com.example.onlineshopproject.ui.Screens.category.AmazingSection
import com.example.onlineshopproject.ui.Screens.category.BestSellsSection
import com.example.onlineshopproject.ui.Screens.category.CategorySection
import com.example.onlineshopproject.ui.Screens.category.MostDiscountSection
import com.example.onlineshopproject.ui.Screens.category.MostFavoriteSection
import com.example.onlineshopproject.ui.Screens.category.MostVisitedSection
import com.example.onlineshopproject.ui.Screens.category.SuperMarketSection
import com.example.onlineshopproject.ui.Screens.login.LoginSection
import com.example.onlineshopproject.ui.Screens.productDetail.NewCommentScreen
import com.example.onlineshopproject.ui.Screens.productDetail.ProductDetailSection
import com.example.onlineshopproject.ui.Screens.profile.ProfileSection

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.Splash.route) {
        composable(Screens.Splash.route){
            SplashSection(navController = navHostController)
        }
        composable(Screens.Home.route){
            HomeSection(navController = navHostController)
        }

        composable(Screens.Login.route){
            LoginSection(navController = navHostController)
        }

        composable(Screens.Profile.route){
            ProfileSection(navController = navHostController)
        }

        composable(Screens.Basket.route){
            BasketSection(navController = navHostController)
        }

        composable(
            route = Screens.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url"){
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ){
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreenSection(navController = navHostController, url = url)
            }
        }

        composable(Screens.Category.route){
            CategorySection(navHostController = navHostController)
        }

        composable(Screens.Amazing.route){
            AmazingSection(navController = navHostController)
        }

        composable(Screens.SuperMarket.route){
            SuperMarketSection(navController = navHostController)
        }

        composable(Screens.BestSells.route){
            BestSellsSection(navController = navHostController)
        }

        composable(Screens.MostDiscount.route){
            MostDiscountSection(navController = navHostController)
        }

        composable(Screens.MostFavorite.route){
            MostFavoriteSection(navController = navHostController)
        }

        composable(Screens.MostVisited.route){
            MostVisitedSection(navController = navHostController)
        }

        composable(Screens.Address.route){
            PickAddressSection(navController = navHostController)
        }

        composable(
            route = Screens.ProductDetail.route + "?productId={productId}",
            arguments = listOf(navArgument("productId"){
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ){
            val productId = it.arguments?.getString("productId")
            productId?.let {
                ProductDetailSection(navController = navHostController, productId = productId)
            }
        }

        composable(route = Screens.NewComment.route + "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("productId"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("productName"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("imageUrl"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId->
                it.arguments!!.getString("productName")?.let { productName->
                    it.arguments!!.getString("imageUrl")?.let { imageUrl->
                        NewCommentScreen(
                            navController = navHostController,
                            productId = productId,
                            productName = productName,
                            imageUrl = imageUrl
                        )
                    }
                }
            }
        }
        composable(route = Screens.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("orderPrice"){
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("orderId")?.let { orderId->
                it.arguments!!.getString("orderPrice")?.let { orderPrice->
                    ConfirmPurchaseScreen(
                        navController = navHostController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }
        }

    }
}