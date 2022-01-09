package com.timkwali.sabicash_exercise.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timkwali.sabicash_exercise.R
import com.timkwali.sabicash_exercise.presentation.ui.components.NewsListItem
import com.timkwali.sabicash_exercise.presentation.ui.theme.Dark100
import com.timkwali.sabicash_exercise.presentation.ui.theme.Dark300

@Preview
@Composable
fun NewsFeedScreen() {
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

                LazyColumn(
                    modifier = Modifier
                        .background(Dark100)
                        .padding(horizontal = 5.dp)
                ) {
                    items(100) {
                        val painter = painterResource(id = R.drawable.home)
                        NewsListItem(painter = painter)
                    }
                }
            }
        }
    )
}

@Composable
fun NewsList() {

}