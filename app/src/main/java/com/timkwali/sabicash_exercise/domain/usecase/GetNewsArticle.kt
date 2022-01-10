package com.timkwali.sabicash_exercise.domain.usecase

import android.util.Log
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.repository.NewsArticlesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsArticle @Inject constructor(
    private val repository: NewsArticlesRepository
) {

    operator fun invoke(): Flow<List<NewsArticle>> = flow {
        emit(repository.getNewsArticles().articles)
    }
}