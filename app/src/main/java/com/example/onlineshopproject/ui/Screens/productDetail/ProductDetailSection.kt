package com.example.onlineshopproject.ui.Screens.productDetail


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.data.model.basket.CartItem
import com.example.onlineshopproject.data.model.poductDetail.Comment
import com.example.onlineshopproject.data.model.poductDetail.ProductDetail
import com.example.onlineshopproject.data.model.poductDetail.SliderImage
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.components.OurLoading
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.backBackground
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.mainColor
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.Constants.PRE_SCREEN
import com.example.onlineshopproject.viewModel.BasketViewModel
import com.example.onlineshopproject.viewModel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailSection(
    navController: NavController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    dataBaseViewModel: BasketViewModel = hiltViewModel(),
) {
    var productDetailResponse by remember { mutableStateOf(ProductDetail()) }
    var imageSliderList by remember { mutableStateOf(emptyList<SliderImage>()) }
    var price by remember { mutableStateOf(0L) }
    var discount by remember { mutableStateOf(0) }
    var description by remember { mutableStateOf("") }
    var commentCount by remember { mutableStateOf(0) }
    var commentList by remember { mutableStateOf(emptyList<Comment>()) }
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(true) }

    //call api
    viewModel.getProductById(productId)

    LaunchedEffect(true) {
        viewModel.productResponse.collectLatest { amazingItemsResult ->
            when (amazingItemsResult) {
                is NetworkResult.Success -> {
                    amazingItemsResult.data?.let {
                        productDetailResponse = it
                        imageSliderList = productDetailResponse.imageSlider ?: emptyList()
                        price = productDetailResponse.price ?: 0L
                        discount = productDetailResponse.discountPercent ?: 0
                        description = productDetailResponse.description ?: ""
                        commentCount = productDetailResponse.commentCount ?: 0
                        commentList = productDetailResponse.comments ?: emptyList()
                        name = productDetailResponse.name ?: ""
                        id = productDetailResponse._id ?: ""
                    }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("6191", "productDetail api ${amazingItemsResult.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }

    if (!loading) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = LocalSpacing.current.small)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.backBackground)
                            .clickable {
                                navController.navigate(PRE_SCREEN) {
                                    popUpTo(Screens.ProductDetail.route) {
                                        inclusive = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "close",
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            item { ImageSlider(imageSliderList) }
            item {
                Spacer(modifier = Modifier.height(LocalSpacing.current.semiSmall))
                Text(
                    text = productDetailResponse.name ?: "",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
                Text(
                    text = "دسته بندی / ${productDetailResponse.category}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = Typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.mainColor
                )
            }
            item {
                Spacer(modifier = Modifier.height(LocalSpacing.current.biggerMedium))
                StarWithText(item = productDetailResponse)
                Spacer(modifier = Modifier.height(LocalSpacing.current.large))
            }
            item {
                ProductButton(price = price, discount = discount, haveDiscount = true) {
                    //todo do nothing........!!!!!!
                }
                Spacer(modifier = Modifier.height(LocalSpacing.current.small))
                ProductButton(price = price, discount = discount) {
                    productDetailResponse?.let {

                    }
                    dataBaseViewModel.insertCartItem(
                        CartItem(
                            id = productDetailResponse._id!!,
                            discountPercent = productDetailResponse.discountPercent!!,
                            image = imageSliderList[0].image,
                            name = productDetailResponse.name!!,
                            price = productDetailResponse.price!!,
                            seller = productDetailResponse.seller!!,
                            count = 1
                        )
                    )
                    navController.navigate(Screens.Basket.route){
                        popUpTo(Screens.ProductDetail.route){
                            inclusive = true
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(LocalSpacing.current.biggerMedium + LocalSpacing.current.small))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.medium)
                        .background(MaterialTheme.colors.unSelectedBottomBar)
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.biggerMedium + LocalSpacing.current.small))
            }
            item { DescriptionSection(description) }
            item {
                Spacer(modifier = Modifier.height(LocalSpacing.current.biggerMedium + LocalSpacing.current.small))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.medium)
                        .background(MaterialTheme.colors.unSelectedBottomBar)
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.biggerMedium + LocalSpacing.current.small))
            }
            item { UserComments(commentCount = commentCount ,items = commentList) }
            item {
                Spacer(modifier = Modifier.height(LocalSpacing.current.biggerMedium + LocalSpacing.current.small))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.medium)
                        .background(MaterialTheme.colors.unSelectedBottomBar)
                )
                Spacer(modifier = Modifier.height(LocalSpacing.current.biggerMedium + LocalSpacing.current.small))
            }
            item {
                WriteYourCommentButton{
                    navController.navigate(Screens.NewComment.route + "?productId=${id}?productName=${name}?imageUrl=${imageSliderList[0].image}")
                }
                Spacer(modifier = Modifier.height(LocalSpacing.current.biggerMedium + LocalSpacing.current.small))
            }
        }
    } else {
        val config = LocalConfiguration.current
        OurLoading(config.screenHeightDp.dp, true)
    }
}
