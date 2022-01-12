package com.timkwali.sabicash_exercise.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.usecase.GetNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(
    private val getNewsArticle: GetNewsArticle
): ViewModel()  {

    private val newsArticles: Flow<PagingData<NewsArticle>> = flowOf()
    private var _data = mutableStateOf(newsArticles)
    val data: State<Flow<PagingData<NewsArticle>>> = _data

    init {
        getNewsArticles()
    }

    private fun getNewsArticles() {
        viewModelScope.launch {
            _data.value = getNewsArticle.invoke()
        }
   }
}