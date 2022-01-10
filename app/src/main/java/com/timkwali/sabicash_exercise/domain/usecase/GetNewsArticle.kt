package com.timkwali.sabicash_exercise.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.repository.NewsArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsArticle @Inject constructor(
    private val repository: NewsArticlesRepository
) {

    suspend operator fun invoke(): Flow<PagingData<NewsArticle>> {
        return repository.getNewsArticles()
    }
}