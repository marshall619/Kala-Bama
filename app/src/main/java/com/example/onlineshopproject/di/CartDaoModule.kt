package com.example.onlineshopproject.di

import com.example.onlineshopproject.data.db.CartDao
import com.example.onlineshopproject.data.db.KalaBamaDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CartDaoModule {

    @Singleton
    @Provides
    fun provideCartDao(
        dataBase: KalaBamaDataBase
    ): CartDao = dataBase.cartDao()

}