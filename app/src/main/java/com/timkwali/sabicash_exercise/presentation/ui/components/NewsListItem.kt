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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timkwali.sabicash_exercise.presentation.ui.theme.Dark100
import com.timkwali.sabicash_exercise.presentation.ui.theme.FadeWhite

@Composable
fun NewsListItem(
    modifier: Modifier = Modifier,
    painter: Painter
) {
    Column {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                },
            shape = RoundedCornerShape(1.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(170.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
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
                                startY = 120f
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
                            text = "News headline goes here",
                            style = TextStyle(color = Color.White, fontSize = 14.sp)
                        )
                        Spacer(modifier = modifier.height(5.dp))
                        Text(
                            text = "News Author",
                            style = TextStyle(color = FadeWhite, fontSize = 12.sp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.heightIn(5.dp))
    }
}