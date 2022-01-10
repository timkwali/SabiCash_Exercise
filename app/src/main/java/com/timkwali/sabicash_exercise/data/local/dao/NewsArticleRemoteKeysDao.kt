package com.timkwali.sabicash_exercise.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timkwali.sabicash_exercise.domain.model.NewsArticleRemoteKeys

@Dao
interface NewsArticleRemoteKeysDao {

    @Query("SELECT * FROM news_articles_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): NewsArticleRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllRemoteKeys(remoteKeys: List<NewsArticleRemoteKeys>)

    @Query("DELETE FROM news_articles_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}