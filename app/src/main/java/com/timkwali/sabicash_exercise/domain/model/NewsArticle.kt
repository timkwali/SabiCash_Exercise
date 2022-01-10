package com.timkwali.sabicash_exercise.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.timkwali.sabicash_exercise.common.Constants

@Entity(tableName = Constants.NEWS_ARTICLES_TABLE)
data class NewsArticle(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val author: String?,
    val title: String?,
    val urlToImage: String?,
    val url: String?
)
