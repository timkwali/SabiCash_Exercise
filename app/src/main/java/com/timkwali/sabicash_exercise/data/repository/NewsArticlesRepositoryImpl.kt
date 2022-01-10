package com.timkwali.sabicash_exercise.data.repository

import com.timkwali.sabicash_exercise.data.remote.NewsArticleApi
import com.timkwali.sabicash_exercise.domain.model.NewsResponse
import com.timkwali.sabicash_exercise.domain.repository.NewsArticlesRepository
import javax.inject.Inject

class NewsArticlesRepositoryImpl @Inject constructor(
    private val newsArticleApi: NewsArticleApi
): NewsArticlesRepository {

    override suspend fun getNewsArticles(): NewsResponse {
        return newsArticleApi.getNewsArticles()
    }
}