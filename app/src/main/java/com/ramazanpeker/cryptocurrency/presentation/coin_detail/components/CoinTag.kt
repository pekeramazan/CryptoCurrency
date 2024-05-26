package com.ramazanpeker.cryptocurrency.presentation.coin_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CoinTag(
    tag: String
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color.White, Color.Blue, Color.Magenta)
    )

    Box(
        modifier = Modifier
            .drawBehind {

                val cornerRadius = 100.dp.toPx()
                drawRoundRect(
                    brush = gradientBrush,
                    size = size,
                    style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)

                )
            }
            .padding(10.dp)

    ) {
        Text(
            text = tag,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}