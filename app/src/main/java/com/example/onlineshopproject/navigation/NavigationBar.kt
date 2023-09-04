package com.example.onlineshopproject.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.onlineshopproject.R
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.bottomBar
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.selectedBottomBar
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.Constants.PRE_ROUTE

@Composable
fun NavigationBarScreen(
    navController: NavController,
    onItemClick: (BottomBarItem) -> Unit,
    //viewModel: BasketViewModel = hiltViewModel(),
) {

    val items = listOf(
        BottomBarItem(
            name = "خانه",
            route = Screens.Home.route,
            selectedItem = painterResource(id = R.drawable.home_fill),
            deSelectedItem = painterResource(id = R.drawable.home_outline)
        ),
        BottomBarItem(
            name = "دسته بندی",
            route = Screens.Category.route,
            selectedItem = painterResource(id = R.drawable.category_fill),
            deSelectedItem = painterResource(id = R.drawable.category_outline)
        ),
        BottomBarItem(
            name = "سبد خرید",
            route = Screens.Basket.route,
            selectedItem = painterResource(id = R.drawable.cart_fill),
            deSelectedItem = painterResource(id = R.drawable.cart_outline)
        ),
        BottomBarItem(
            name = "پروفایل",
            route = Screens.Profile.route,
            selectedItem = painterResource(id = R.drawable.user_fill),
            deSelectedItem = painterResource(id = R.drawable.user_outline)
        )
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }
    PRE_ROUTE = backStackEntry.value?.destination?.route.toString()

    if (showBottomBar) {
        BottomNavigation(
            modifier = Modifier.height(60.dp),
            backgroundColor = MaterialTheme.colors.bottomBar,
            elevation = 5.dp
        ) {
            items.forEachIndexed { index, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = MaterialTheme.colors.selectedBottomBar,
                    unselectedContentColor = MaterialTheme.colors.unSelectedBottomBar,
                    icon = {
                        //val cartCounter by viewModel.currentCartItemCount.collectAsState(initial = 0)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (selected) {
//                                if(cartCounter > 0 && index == 2){
//                                    IconWithBadge(cartCounter , item.selectedIcon)
//                                }else{
//
//                                }
                                Icon(
                                    modifier = Modifier.height(24.dp),
                                    painter = item.selectedItem,
                                    contentDescription = item.name
                                )
                            } else {
//                                if (cartCounter > 0 && index == 2){
//                                    IconWithBadge(cartCounter , item.deSelectedIcon)
//                                }else{
//
//                                }
                                Icon(
                                    modifier = Modifier.height(24.dp),
                                    painter = item.deSelectedItem,
                                    contentDescription = item.name
                                )
                            }
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                style = Typography.h6,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}