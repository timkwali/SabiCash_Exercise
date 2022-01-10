package com.timkwali.sabicash_exercise.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.timkwali.sabicash_exercise.common.Constants
import com.timkwali.sabicash_exercise.data.local.NewsArticleDatabase
import com.timkwali.sabicash_exercise.data.paging.NewsArticleRemoteMediator
import com.timkwali.sabicash_exercise.data.remote.NewsArticleApi
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.model.NewsResponse
import com.timkwali.sabicash_exercise.domain.repository.NewsArticlesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsArticlesRepositoryImpl @Inject constructor(
    private val newsArticleApi: NewsArticleApi,
    private val newsArticleDatabase: NewsArticleDatabase
): NewsArticlesRepository {

    @ExperimentalPagingApi
    override suspend fun getNewsArticles(): Flow<PagingData<NewsArticle>> {
        val pagingSourceFactory = { newsArticleDatabase.newsArticleDao().getAllNewsArticles() }
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_PER_PAGE),
            remoteMediator = NewsArticleRemoteMediator(
                newsArticleApi = newsArticleApi,
                newsArticleDatabase = newsArticleDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}