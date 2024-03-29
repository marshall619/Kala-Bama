package com.example.onlineshopproject.ui.Screens.productDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.example.onlineshopproject.data.model.poductDetail.SliderImage
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(item : List<SliderImage>){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(
                vertical = LocalSpacing.current.small
            )
    ) {
        val pagerState = rememberPagerState()
        var imageUrl by remember { mutableStateOf("") }

        Box() {

            HorizontalPager(
                count = item.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { index ->
                imageUrl = item[index].image
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = imageUrl)
                            .apply(
                                block = fun ImageRequest.Builder.() {
                                    scale(Scale.FILL)
                                }
                            )
                            .build()
                    )
                    Image(
                        painter = painter, contentDescription = "", modifier = Modifier
                            .padding(LocalSpacing.current.biggerMedium)
                            .clip(LocalShape.current.medium),
                        contentScale = ContentScale.FillBounds
                    )

                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = LocalSpacing.current.semiLarge),
                activeColor = Color.Black,
                inactiveColor = Color.LightGray,
                indicatorWidth = LocalSpacing.current.small,
                indicatorHeight = LocalSpacing.current.small,
                indicatorShape = CircleShape
            )
        }

    }
}