package com.timkwali.sabicash_exercise.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.timkwali.sabicash_exercise.common.Constants

@Entity(tableName = Constants.NEWS_ARTICLES_REMOTE_KEYS_TABLE)
data class NewsArticleRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
