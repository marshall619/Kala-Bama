package com.example.onlineshopproject.di

import com.example.onlineshopproject.data.remote.CheckOutApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent :: class)
object CheckOutApiModule {

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit) : CheckOutApiInterface =
    retrofit.create(CheckOutApiInterface::class.java)

}