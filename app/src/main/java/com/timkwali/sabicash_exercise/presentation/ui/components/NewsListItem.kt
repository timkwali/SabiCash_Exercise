package com.timkwali.sabicash_exercise.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.timkwali.sabicash_exercise.R
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.presentation.ui.theme.FadeWhite

@Composable
fun NewsListItem(
    newsArticle: NewsArticle,
    onClick: (url: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Card(
            modifier = modifier
                .fillMaxSize()
                .clickable {
                   onClick(newsArticle.url.orEmpty())
                },
            shape = RoundedCornerShape(1.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = newsArticle.urlToImage,
                        builder = {
                            crossfade(700)
                            placeholder(R.drawable.ic_placeholder)
                            error(R.drawable.ic_placeholder)
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = -10f
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Text(
                            text = newsArticle.title.orEmpty(),
                            style = TextStyle(color = Color.White, fontSize = 14.sp)
                        )
                        Spacer(modifier = modifier.height(5.dp))
                        Text(
                            text = newsArticle.author.orEmpty(),
                            style = TextStyle(color = FadeWhite, fontSize = 12.sp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.heightIn(5.dp))
    }
}