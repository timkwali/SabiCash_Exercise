package com.timkwali.sabicash_exercise.presentation.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.timkwali.sabicash_exercise.R
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.presentation.ui.components.*
import com.timkwali.sabicash_exercise.presentation.ui.theme.Dark100
import com.timkwali.sabicash_exercise.presentation.ui.theme.Dark300
import com.timkwali.sabicash_exercise.presentation.viewmodel.NewsArticleViewModel
import kotlinx.coroutines.flow.Flow

@Preview
@Composable
fun NewsFeedScreen(viewModel: NewsArticleViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.news_feed), color = Color.White)
                },
                backgroundColor = Dark300
            )
        },
        content = {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Dark100)
            ) {
                Text(
                    text = stringResource(id = R.string.top_news),
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
                NewsArticleList(articlesList = viewModel.getNewsArticles())
            }
        }
    )
}

@Composable
fun NewsArticleList(articlesList: Flow<PagingData<NewsArticle>>) {
    val context = LocalContext.current
    val articleListItem: LazyPagingItems<NewsArticle> = articlesList.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .background(Dark100)
            .padding(horizontal = 5.dp)
    ) {
        items(
            items = articleListItem,
            key = { newsArticle ->
                newsArticle.id
            }
        ) { newsArticle ->
            newsArticle?.let { it ->
                NewsListItem(
                    it,
                    onClick = { url ->
                        val browserIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(url)
                        )
                        startActivity(context, browserIntent, null)
                    }
                )
            }
        }


        articleListItem.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingIndicator(
                        modifier = Modifier.fillParentMaxSize()
                    ) }
                }
                loadState.refresh is LoadState.Error -> {
                    val exception = articleListItem.loadState.refresh as LoadState.Error
                    item {
                        ErrorIndicator(
                            errorMessage = exception.error.localizedMessage ?: "",
                            onRetry = { retry() }
                        )
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { ProgressIndicator() }
                }
                loadState.append is LoadState.Error -> {
                    val exception = articleListItem.loadState.append as LoadState.Error
                    item {
                        ErrorIndicator(
                            errorMessage = exception.error.localizedMessage ?: "",
                            onRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}