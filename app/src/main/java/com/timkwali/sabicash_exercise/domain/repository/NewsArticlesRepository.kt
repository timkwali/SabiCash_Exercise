package com.timkwali.sabicash_exercise.domain.repository

import androidx.paging.PagingData
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.model.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsArticlesRepository {
    suspend fun getNewsArticles(): Flow<PagingData<NewsArticle>>
}