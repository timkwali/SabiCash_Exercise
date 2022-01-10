package com.timkwali.sabicash_exercise.di

import android.content.Context
import androidx.room.Room
import com.timkwali.sabicash_exercise.common.Constants
import com.timkwali.sabicash_exercise.data.local.NewsArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        @ApplicationContext context: Context
//    ): NewsArticleDatabase {
//        return Room.databaseBuilder(
//            context,
//            NewsArticleDatabase::class.java,
//            Constants.NEWS_ARTICLES_DATABASE
//        ).build()
//    }
//}