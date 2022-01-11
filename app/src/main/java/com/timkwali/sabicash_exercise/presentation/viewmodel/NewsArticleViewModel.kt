package com.timkwali.sabicash_exercise.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.usecase.GetNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(
    private val getNewsArticle: GetNewsArticle
): ViewModel()  {

    init {
        getNewsArticles()
    }

    private lateinit var _data:Flow<PagingData<NewsArticle>>
    val data get() = _data

    private fun getNewsArticles() {
        viewModelScope.launch {
            _data = getNewsArticle.invoke()
        }
   }
}