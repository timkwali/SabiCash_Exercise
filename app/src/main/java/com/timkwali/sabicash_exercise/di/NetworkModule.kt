package com.timkwali.sabicash_exercise.di

import android.content.Context
import androidx.room.Room
import com.timkwali.sabicash_exercise.BuildConfig
import com.timkwali.sabicash_exercise.common.Constants
import com.timkwali.sabicash_exercise.data.local.NewsArticleDatabase
import com.timkwali.sabicash_exercise.data.remote.NewsArticleApi
import com.timkwali.sabicash_exercise.data.repository.NewsArticlesRepositoryImpl
import com.timkwali.sabicash_exercise.domain.repository.NewsArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    @Singleton
    fun provideClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15L, TimeUnit.SECONDS)
            .readTimeout(15L, TimeUnit.SECONDS)
            .writeTimeout(15L, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsArticleApi(retrofit: Retrofit): NewsArticleApi {
        return retrofit.create(NewsArticleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsArticleDatabase {
        return Room.databaseBuilder(
            context,
            NewsArticleDatabase::class.java,
            Constants.NEWS_ARTICLES_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsArticleRepository(
        newsArticleApi: NewsArticleApi,
        newsArticleDatabase: NewsArticleDatabase
    ): NewsArticlesRepository {
        return NewsArticlesRepositoryImpl(
            newsArticleApi,
            newsArticleDatabase
        )
    }
}