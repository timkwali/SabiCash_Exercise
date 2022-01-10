package com.timkwali.sabicash_exercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.timkwali.sabicash_exercise.data.repository.NewsArticlesRepositoryImpl
import com.timkwali.sabicash_exercise.presentation.ui.screens.NewsFeedScreen
import com.timkwali.sabicash_exercise.presentation.ui.theme.SabiCash_ExerciseTheme
import com.timkwali.sabicash_exercise.presentation.viewmodel.NewsArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: NewsArticleViewModel by viewModels()

        setContent {
            SabiCash_ExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    vm
                    NewsFeedScreen()
                }
            }
        }
    }
}