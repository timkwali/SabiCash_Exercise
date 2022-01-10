package com.timkwali.sabicash_exercise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timkwali.sabicash_exercise.data.local.dao.NewsArticleDao
import com.timkwali.sabicash_exercise.data.local.dao.NewsArticleRemoteKeysDao
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.model.NewsArticleRemoteKeys

@Database(
    entities = [NewsArticle::class, NewsArticleRemoteKeys::class],
    version = 1
)
abstract class NewsArticleDatabase: RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao

    abstract fun newsArticleRemoteKeysDao(): NewsArticleRemoteKeysDao
}