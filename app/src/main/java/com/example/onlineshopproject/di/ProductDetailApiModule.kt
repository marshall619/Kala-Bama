package com.example.onlineshopproject.di

import com.example.onlineshopproject.data.remote.ProductDetailApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent :: class)
object ProductDetailApiModule {

    @Provides
    @Singleton
    fun provideProductDetailApi(retrofit: Retrofit) : ProductDetailApiInterface =
    retrofit.create(ProductDetailApiInterface::class.java)

}