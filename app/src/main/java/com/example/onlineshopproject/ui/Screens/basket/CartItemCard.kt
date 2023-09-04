package com.example.onlineshopproject.ui.Screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.basket.CartItem
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.backgroundAddBasket
import com.example.onlineshopproject.ui.theme.backgroundTrashBasket
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.h4
import com.example.onlineshopproject.ui.theme.h5
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.ui.theme.plusBasket
import com.example.onlineshopproject.ui.theme.trashColorBasket
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.DigitHelper
import com.example.onlineshopproject.util.DigitHelper.applyDiscount
import com.example.onlineshopproject.util.DigitHelper.digitByLocate
import com.example.onlineshopproject.util.DigitHelper.digitBySeparator
import com.example.onlineshopproject.viewModel.BasketViewModel

@Composable
fun CartItemCard(item: CartItem, viewModel: BasketViewModel = hiltViewModel()) {

    var itemCount by remember { mutableStateOf(item.count) }

    var totalPrice by remember {
        mutableStateOf(
            applyDiscount(
                item.price,
                item.discountPercent
            )
        )
    }

    var totalPriceNoDiscount by remember { mutableStateOf(item.price) }

    totalPrice *= itemCount
    totalPriceNoDiscount *= itemCount

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.biggerMedium),
        elevation = 4.dp,
        shape = LocalShape.current.small
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(.30f)
                    .padding(LocalSpacing.current.extraSmall)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.backgroundTrashBasket)
                        .clickable { viewModel.removeFromCart(item) },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trashbin),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(.1f))
            Column(
                modifier = Modifier
                    .weight(.65f)
                    .padding(top = LocalSpacing.current.medium)
            ) {
                Text(
                    text = item.name,
                    style = Typography.h5,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = LocalSpacing.current.semiSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "تعداد :",
                        style = Typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.unSelectedBottomBar
                    )

                    Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.backgroundAddBasket)
                            .clickable {
                                itemCount++
                                viewModel.changeCountCartItem(item.id, itemCount)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            tint = MaterialTheme.colors.plusBasket
                        )
                    }

                    Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))

                    Text(
                        text = digitByLocate(itemCount.toString()),
                        style = Typography.h3,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.backgroundTrashBasket)
                            .clickable {
                                if (itemCount > 1) {
                                    itemCount--
                                    viewModel.changeCountCartItem(item.id, itemCount)
                                } else {
                                    viewModel.removeFromCart(item)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Divider(
                            modifier = Modifier
                                .height(2.dp)
                                .width(12.dp)
                                .background(MaterialTheme.colors.trashColorBasket)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = LocalSpacing.current.semiSmall)
                        .padding(end = LocalSpacing.current.small),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "قیمت واحد :",
                        style = Typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.unSelectedBottomBar
                    )

                    Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = digitByLocate(" ${digitBySeparator(item.price.toString())}"),
                            style = Typography.h4,
                            color = MaterialTheme.colors.mainColor,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.toman),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(horizontal = LocalSpacing.current.extraSmall),
                            tint = MaterialTheme.colors.mainColor
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = LocalSpacing.current.semiSmall)
                        .padding(end = LocalSpacing.current.small),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "قیمت کل:",
                        style = Typography.h4,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.unSelectedBottomBar
                    )

                    Spacer(modifier = Modifier.width(LocalSpacing.current.semiSmall))
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = digitByLocate(" ${digitBySeparator((item.price * item.count).toString())}"),
                                style = Typography.h4,
                                color = MaterialTheme.colors.unSelectedBottomBar,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.LineThrough
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.toman),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(horizontal = LocalSpacing.current.extraSmall),
                                tint = MaterialTheme.colors.unSelectedBottomBar
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = digitByLocate(" ${digitBySeparator(applyDiscount((item.price * item.count), item.discountPercent).toString())}"),
                                style = Typography.h4,
                                color = MaterialTheme.colors.mainColor,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.toman),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(horizontal = LocalSpacing.current.extraSmall),
                                tint = MaterialTheme.colors.mainColor
                            )
                        }
                    }

                }
            }
        }
    }
}