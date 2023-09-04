package com.example.onlineshopproject.ui.Screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopproject.R
import com.example.onlineshopproject.navigation.Screens
import com.example.onlineshopproject.ui.theme.LocalShape
import com.example.onlineshopproject.ui.theme.LocalSpacing
import com.example.onlineshopproject.ui.theme.Typography
import com.example.onlineshopproject.ui.theme.h3
import com.example.onlineshopproject.ui.theme.trashColorBasket
import com.example.onlineshopproject.ui.theme.unSelectedBottomBar
import com.example.onlineshopproject.util.Constants
import com.example.onlineshopproject.util.Constants.USER_EMAIL
import com.example.onlineshopproject.util.Constants.USER_FAMILY
import com.example.onlineshopproject.util.Constants.USER_ID_CODE
import com.example.onlineshopproject.util.Constants.USER_NAME
import com.example.onlineshopproject.util.Constants.USER_PHONE
import com.example.onlineshopproject.util.Constants.USER_PHONE2
import com.example.onlineshopproject.viewModel.DataStoreViewModel

@Composable
fun InformationCard(navController: NavController, viewModel: DataStoreViewModel = hiltViewModel()) {

    getDataVariables(viewModel)

    var userName by remember { mutableStateOf(USER_NAME) }
    var userFamily by remember { mutableStateOf(USER_FAMILY) }
    var userPhone by remember { mutableStateOf(if (USER_PHONE.startsWith("09")) USER_PHONE else USER_PHONE2) }
    var userEmail by remember { mutableStateOf(if (USER_PHONE.startsWith("09")) USER_EMAIL else USER_PHONE) }
    var userID by remember { mutableStateOf(USER_ID_CODE) }
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = LocalSpacing.current.small,
                horizontal = LocalSpacing.current.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.dot),
            contentDescription = null,
            modifier = Modifier.size(8.dp),
            tint = Color(0xff008072)
        )
        Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
        Text(
            text = "اطلاعات حساب کاربری",
            style = Typography.h3,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.unSelectedBottomBar
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = LocalSpacing.current.small,
                horizontal = LocalSpacing.current.small
            ),
        elevation = LocalSpacing.current.extraSmall
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //user name ......................................................
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.semiLarge),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "نام",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "*",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.trashColorBasket
                )
            }

            ProfileEditText(
                value = userName,
                placeholder = "نام کاربری",
                onValueChange = { userName = it })

            //family.................................................
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.semiLarge),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "نام خانوادگی",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "*",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.trashColorBasket
                )
            }

            ProfileEditText(
                value = userFamily,
                placeholder = "نام خانوادگی",
                onValueChange = { userFamily = it })

            //phone number.................................................
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.semiLarge),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "شماره تلفن همراه",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "*",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.trashColorBasket
                )
            }
            if (!USER_PHONE.startsWith("09")) {
                ProfileEditText(
                    value = userPhone,
                    placeholder = "شماره تلفن",
                    onValueChange = { userPhone = it })
            } else {
                ProfileEditText(
                    value = userPhone,
                    placeholder = "شماره تلفن",
                    onValueChange = { })
            }
            //user id....................................
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.semiLarge),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "شماره ملی",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "*",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.trashColorBasket
                )
            }
            ProfileEditText(
                value = userID,
                placeholder = "شماره ملی",
                onValueChange = { userID = it })

            //user email....................................
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.semiLarge),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "ایمیل",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            if (USER_PHONE.startsWith("09")) {
                ProfileEditText(
                    value = userEmail,
                    placeholder = "ایمیل",
                    onValueChange = { userEmail = it })
            } else {
                ProfileEditText(
                    value = userEmail,
                    placeholder = "ایمیل",
                    onValueChange = { })
            }

        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .clip(LocalShape.current.extraSmall)
                .background(Color(0xff008072))
                .clickable {
                    if (userName != "" && userFamily != "" && userID != "" && userPhone != "") {
                        viewModel.saveUserName(userName)
                        viewModel.saveUSER_FAMILY(userFamily)
                        viewModel.saveUSER_EMAIL(userEmail)
                        viewModel.saveUSER_IDCODE(userID)
                        viewModel.saveUSER_PHONE_KEY2(userPhone)
                        navController.navigate(Screens.Home.route){
                            popUpTo(Screens.Profile.route){
                                inclusive = true
                            }
                        }
                    } else {
                        Toast
                            .makeText(
                                context,
                                "لطفا فیلد های ستاره دار را پر کنید.",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                }
                .padding(LocalSpacing.current.small)
                .padding(horizontal = LocalSpacing.current.small),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tick),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))
                Text(
                    text = "ذخیره اطلاعات",
                    style = Typography.h3,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }

}

private fun getDataVariables(dataStore: DataStoreViewModel) {
    USER_PHONE2 = dataStore.getUSER_PHONE_KEY2()
    USER_ID_CODE = dataStore.getUSER_IDCODE()
    USER_EMAIL = dataStore.getUSER_EMAIL()
    USER_FAMILY = dataStore.getUSER_FAMILY()
    USER_NAME = dataStore.getUserName()
}