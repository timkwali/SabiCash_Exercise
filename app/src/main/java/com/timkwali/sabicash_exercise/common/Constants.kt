package com.timkwali.sabicash_exercise.common

import com.timkwali.sabicash_exercise.BuildConfig

object Constants {
    const val BASE_URL = "https://newsapi.org/"
    const val API_KEY = BuildConfig.API_KEY
    const val ITEMS_PER_PAGE = 5

    const val NEWS_ARTICLES_DATABASE = "news_articles_database"
    const val NEWS_ARTICLES_REMOTE_KEYS_DATABASE = "news_articles_remote_keys_database"
    const val NEWS_ARTICLES_TABLE = "news_articles_table"
    const val NEWS_ARTICLES_REMOTE_KEYS_TABLE = "news_articles_remote_keys_table"
}