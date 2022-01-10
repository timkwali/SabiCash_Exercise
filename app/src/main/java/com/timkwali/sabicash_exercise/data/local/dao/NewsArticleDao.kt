package com.timkwali.sabicash_exercise.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timkwali.sabicash_exercise.domain.model.NewsArticle

@Dao
interface NewsArticleDao {

    @Query("SELECT * FROM news_articles_table")
    fun getAllNewsArticles(): PagingSource<Int, NewsArticle>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllNewsArticles(newsArticles: List<NewsArticle>)

    @Query("DELETE FROM news_articles_table")
    suspend fun deleteAllNewsArticles()
}