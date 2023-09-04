package com.example.onlineshopproject.di

import com.example.onlineshopproject.data.remote.HomeApiInterface
import com.example.onlineshopproject.data.remote.ProfileApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent :: class)
object HomeApiModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit) : HomeApiInterface =
    retrofit.create(HomeApiInterface::class.java)

}