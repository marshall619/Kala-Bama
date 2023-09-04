package com.example.onlineshopproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlineshopproject.data.db.CartDao
import com.example.onlineshopproject.data.model.basket.CartItem


@Database(entities = [CartItem :: class] , version = 1 , exportSchema = false)
abstract class KalaBamaDataBase : RoomDatabase() {

    abstract fun cartDao() : CartDao

}