package com.example.onlineshopproject.ui.Screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.basket.CartItem
import com.example.onlineshopproject.data.model.home.ProductItem
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.body2
import com.example.onlineshopproject.ui.theme.darkText
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.specialCartColor
import com.example.onlineshopproject.ui.theme.specialRed
import com.example.onlineshopproject.ui.theme.specialTextColor
import com.example.onlineshopproject.util.Constants.PRE_SCREEN
import com.example.onlineshopproject.util.DigitHelper.applyDiscount
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.util.DigitHelper.digitBySeparator
import com.example.onlineshopproject.viewModel.BasketViewModel

@Composable
fun SpecialItemCard(item: ProductItem , navController : NavController , viewModel : BasketViewModel = hiltViewModel()) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .padding(LocalSpacing.current.small)
            .clickable {
                PRE_SCREEN = Screens.Home.route
                navController.navigate(Screens.ProductDetail.route + "?productId=${item._id}")
            },
        elevation = 6.dp,
        shape = LocalShape.current.biggerSmall
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = item.image),
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(bottom = 2.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = item.name,
                style = Typography.h6,
                modifier = Modifier.height(50.dp),
                color = MaterialTheme.colors.darkText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.specialCartColor)
                        .clickable {
                            viewModel.insertCartItem(
                                CartItem(
                                    id = item._id,
                                    discountPercent = item.discountPercent,
                                    image = item.image,
                                    name = item.name,
                                    price = item.price,
                                    seller = item.seller,
                                    count = 1
                                )
                            )
                            navController.navigate(Screens.Basket.route){
                                popUpTo(Screens.Home.route){
                                    inclusive = true
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.shoppingcart),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }

                Column {

                    Text(
                        text = digitByLocate(
                            digitBySeparator(
                                applyDiscount(
                                    item.price,
                                    item.discountPercent
                                ).toString()
                            )
                        ),
                        color = Color.LightGray,
                        style = Typography.body2,
                        textDecoration = TextDecoration.LineThrough
                    )

                    Row {
                        Text(
                            text = digitByLocate(
                                digitBySeparator(
                                    item.price.toString()
                                )
                            ),
                            style = Typography.body2,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.specialTextColor
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.toman),
                            contentDescription = "",
                            modifier = Modifier
                                .size(LocalSpacing.current.semiLarge)
                                .padding(horizontal = LocalSpacing.current.extraSmall),
                            tint = MaterialTheme.colors.specialTextColor
                        )

                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(LocalSpacing.current.small), contentAlignment = Alignment.TopEnd
        ) {
            Column(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.specialRed),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "% ویژه",
                    style = Typography.h6,
                    modifier = Modifier.padding(4.dp),
                    color = Color.White
                )
            }
        }
    }
}