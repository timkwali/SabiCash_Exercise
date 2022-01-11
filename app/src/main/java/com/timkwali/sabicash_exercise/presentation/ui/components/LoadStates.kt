package com.timkwali.sabicash_exercise.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingIndicator(
    modifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ProgressIndicator()
    }
}

@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(
        color = Color.Green,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorIndicator(
    errorMessage: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.padding(all = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.h6,
            color = Color.Red,
            modifier = Modifier.padding(bottom = 14.dp)
        )
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}
