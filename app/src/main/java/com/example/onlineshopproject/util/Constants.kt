package com.example.onlineshopproject.util

import androidx.compose.ui.unit.dp
import com.example.onlineshopproject.BuildConfig

object Constants {
    val LOGO_SIZE = 250.dp
    const val TIMEOUT_IN_SECOND : Long = 60
    const val BASE_URL = "https://truelearn-digikala.iran.liara.run/api/"
    const val API_KEY = BuildConfig.X_API_KEY
    const val SHOPPING_CART_TABLE = "SHOPPING_CART_TABLE"
    const val DATABASE_NAME = "KALA_BAMA_TABLE"
    var USER_PHONE = "USER_PHONE"
    var USER_TOKEN = "USER_TOKEN"
    var USER_PASSWORD = "USER_PASSWORD"
    var USER_ID = "USER_ID"
    var DATASTORE_NAME = "Kala_Bama_dataStore"
    var KEY = BuildConfig.KEY
    var IV = BuildConfig.IV
    var PRE_ROUTE = ""
    var PRE_SCREEN = ""
    var ZARINPAL_MERCHANT_ID = ""
    var USER_NAME = ""
    var USER_FAMILY = ""
    var USER_EMAIL = ""
    var USER_ID_CODE = ""
    var USER_PHONE2 = ""

    const val AUCTION_URL = "https://www.digistyle.com/sale-landing/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=style&promo_name=style&promo_position=circle_badge"
    const val PINDO_URL = "https://www.pindo.ir/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=pindo&promo_name=pindo&promo_position=circle_badge"
    const val SHOPPING_URL = "https://www.digikala.com/landings/village-businesses/?promo_name=boomi-landing&promo_position=circle_badge"
    const val GIFT_CARD_URL = "https://www.digikala.com/landing/gift-card-landing/?promo_name=gift_landing&promo_position=circle_badge"
}