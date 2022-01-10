package com.timkwali.sabicash_exercise.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timkwali.sabicash_exercise.domain.usecase.GetNewsArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(
    private val getNewsArticle: GetNewsArticle
): ViewModel()  {

    init {
        getNewsArticles()
    }

    private fun getNewsArticles() = viewModelScope.launch {
        getNewsArticle.invoke().collect {
            Log.d("newslsfknaj",it.toString())
        }
    }
}