package com.example.onlineshopproject.di

import com.example.onlineshopproject.data.remote.ProfileApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent :: class)
object ProfileApiModule {

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit) : ProfileApiInterface =
    retrofit.create(ProfileApiInterface::class.java)

}