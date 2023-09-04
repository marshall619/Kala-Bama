package com.example.onlineshopproject.ui.Screens.productDetail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.onlineshopproject.R
import com.example.onlineshopproject.data.model.poductDetail.NewComment
import com.example.onlineshopproject.data.remote.NetworkResult
import com.example.onlineshopproject.ui.components.OurLoading
import com.example.onlineshopproject.ui.theme.DarkCyan
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.amber
import com.example.onlineshopproject.ui.theme.darkText
import com.example.onlineshopproject.ui.theme.grayAlpha
import com.example.onlineshopproject.ui.theme.grayCategory
import com.example.onlineshopproject.ui.theme.h2
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.h4
import com.example.onlineshopproject.ui.theme.h5
import com.example.onlineshopproject.ui.theme.h6
import com.example.onlineshopproject.ui.theme.semiDarkText
import com.example.onlineshopproject.util.Constants.USER_TOKEN
import com.example.onlineshopproject.viewModel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NewCommentScreen(
    navController: NavController,
    productId: String,
    productName: String,
    imageUrl: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Header(navController, productName, imageUrl)
        CommentForm(navController, productId)
    }
}

@Composable
private fun Header(
    navController: NavController,
    productName: String,
    imageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = LocalSpacing.current.extraSmall,
                horizontal = LocalSpacing.current.small
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
            )
        }

        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.small)
                .clip(LocalShape.current.small)
                .size(50.dp)

        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.your_comment),
                style = Typography.h3,
                color = MaterialTheme.colors.darkText
            )
            Text(
                text = productName,
                style = Typography.h6,
                color = MaterialTheme.colors.semiDarkText
            )
        }

    }
    Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)


}

@Composable
fun CommentForm(
    navController: NavController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    var sliderValue by remember {
        mutableStateOf(0f)
    }

    var userName by remember{ mutableStateOf("کاربر مهمان")}

    val score = when (sliderValue.toInt()) {
        1 -> ""
        2 -> stringResource(id = R.string.very_bad)
        3 -> stringResource(id = R.string.bad)
        4 -> stringResource(id = R.string.normal)
        5 -> stringResource(id = R.string.good)
        6 -> stringResource(id = R.string.very_good)
        else -> ""
    }


    Text(
        text = score,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = LocalSpacing.current.medium),
        style = Typography.h2,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.darkText,
        textAlign = TextAlign.Center
    )

    Slider(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.large),
        value = sliderValue,
        onValueChange = { _sliderValue ->
            sliderValue = _sliderValue
        },
        onValueChangeFinished = {
            Log.d("3636", "sliderValue = $sliderValue")
        },
        valueRange = 1f..6f,
        steps = 4,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.amber,
            activeTrackColor = MaterialTheme.colors.amber,
            inactiveTrackColor = MaterialTheme.colors.grayCategory,
            activeTickColor = MaterialTheme.colors.amber,
            inactiveTickColor = MaterialTheme.colors.grayAlpha
        )
    )


    Divider(
        modifier = Modifier
            .padding(top = LocalSpacing.current.semiMedium),
        color = MaterialTheme.colors.grayCategory,
        thickness = 1.dp,
    )

    /////////////////////////////////////////////

    var commentTitle by remember { mutableStateOf("") }
    var commentBody by remember { mutableStateOf("") }

    val context = LocalContext.current


    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {

        viewModel.newCommentResponse.collectLatest { newCommentResult ->
            when (newCommentResult) {
                is NetworkResult.Success -> {
                    if (newCommentResult.message.equals("کامنت با موفقیت ثبت شد")) {
                        navController.popBackStack()
                    }
                    loading = false
                }
                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${newCommentResult.message}")
                }
                is NetworkResult.Loading -> {

                }

            }
        }

    }


    Column(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.medium)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = LocalSpacing.current.medium),
            text = stringResource(id = R.string.say_your_comment),
            style = Typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.darkText,
        )



        Text(
            modifier = Modifier
                .padding(LocalSpacing.current.extraSmall),
            text = stringResource(id = R.string.comment_title),
            style = Typography.h5,
            color = MaterialTheme.colors.darkText,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = commentTitle,
            onValueChange = { commentTitle = it },
            maxLines = 1,
            singleLine = true,
            shape = LocalShape.current.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.grayCategory,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )


        Text(
            modifier = Modifier
                .padding(
                    top = LocalSpacing.current.biggerMedium,
                    start = LocalSpacing.current.extraSmall,
                    bottom = LocalSpacing.current.extraSmall,
                ),
            text = stringResource(id = R.string.comment_text),
            style = Typography.h5,
            color = MaterialTheme.colors.darkText,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = commentBody,
            onValueChange = { commentBody = it },
            shape = LocalShape.current.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.darkText,
                backgroundColor = MaterialTheme.colors.grayCategory,
                focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            maxLines = 3,
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LocalSpacing.current.small,
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val checkedState = remember { mutableStateOf(false) }
            userName = if(checkedState.value) {
                "کاربر ناشناس"
            }else{
                "کاربر مهمان"
            }
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(MaterialTheme.colors.DarkCyan)
            )
            Text(
                text = stringResource(id = R.string.comment_anonymously),
                style = Typography.h5,
                color = MaterialTheme.colors.darkText,
            )
        }

        Divider(color = MaterialTheme.colors.grayCategory, thickness = 2.dp)


        if (loading) {
            OurLoading(height = 60.dp, isDark = true)
        } else {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = LocalSpacing.current.medium
                    ),
                onClick = {
                    loading = true
                    val newComment = NewComment(
                        token = USER_TOKEN,
                        productId = productId,
                        star = (sliderValue - 1).toInt(),
                        title = commentTitle,
                        description = commentBody,
                        userName = userName //todo change user name
                    )
                    if (newComment.title.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_title_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.star == 0) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_star_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.description.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_body_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        viewModel.setNewComment(newComment)
                    }

                }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = LocalSpacing.current.extraSmall),

                    text = stringResource(id = R.string.set_new_comment),
                    textAlign = TextAlign.Center,
                    style = Typography.h4,
                    color = MaterialTheme.colors.semiDarkText
                )
            }
        }


    }


}