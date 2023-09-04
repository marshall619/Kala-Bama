package com.example.onlineshopproject.di

import com.example.onlineshopproject.data.remote.CategoryApiInterface
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
object CategoryApiModule {

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit) : CategoryApiInterface =
    retrofit.create(CategoryApiInterface::class.java)

}