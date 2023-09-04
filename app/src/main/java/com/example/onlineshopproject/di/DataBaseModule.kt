package com.example.onlineshopproject.di

import android.content.Context
import androidx.room.Room
import com.example.onlineshopproject.data.db.KalaBamaDataBase
import com.example.onlineshopproject.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBaseModule(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(
        context,
        KalaBamaDataBase :: class.java,
        DATABASE_NAME
    ).build()

}