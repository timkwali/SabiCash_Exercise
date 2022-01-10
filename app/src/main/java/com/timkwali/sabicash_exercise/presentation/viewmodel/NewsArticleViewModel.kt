package com.timkwali.sabicash_exercise.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.usecase.GetNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(
    private val getNewsArticle: GetNewsArticle
): ViewModel()  {

    init {
//        getNewsArticles()
    }


    fun getNewsArticles(): Flow<PagingData<NewsArticle>> {
        var a: Flow<PagingData<NewsArticle>> = flow {  }
        viewModelScope.launch {
            a = getNewsArticle.invoke()
        }
        return a
   }
}