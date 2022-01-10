package com.timkwali.sabicash_exercise.data.repository

import com.timkwali.sabicash_exercise.data.api.NewsArticleApi
import com.timkwali.sabicash_exercise.domain.model.NewsResponse
import com.timkwali.sabicash_exercise.domain.repository.NewsArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NewsArticlesRepositoryImpl @Inject constructor(
    private val newsArticleApi: NewsArticleApi
): NewsArticlesRepository {

    override suspend fun getNewsArticles(): NewsResponse {
        return newsArticleApi.getNewsArticles()
    }
}