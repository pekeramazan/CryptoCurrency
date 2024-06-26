package com.ramazanpeker.cryptocurrency.presentation.coin_list.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ramazanpeker.cryptocurrency.domain.model.Coin

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit,
    modifier: Modifier = Modifier
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(Color.White, Color.Blue, Color.Magenta)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()

            .clickable { onItemClick(coin) }
            .padding(vertical = 8.dp)
            .background(color = Color.Black,
                shape = RoundedCornerShape(12.dp),
            )
            .drawBehind {

                val cornerRadius = 12.dp.toPx()
                drawRoundRect(
                    brush = gradientBrush,
                    size = size,
                    style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)

                )
            }, verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Spacer(modifier = Modifier.width(20.dp))

        Box(
            modifier = Modifier
                .size(25.dp)
                .background(Color.Blue, CircleShape),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "${coin.rank}",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            Text(
                text = "${coin.name} (${coin.symbol})",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = if (coin.isActive == true) "Active" else "Inactive",
                color = Color.White.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Image(
            painter = rememberAsyncImagePainter(model = "https://static.coinpaprika.com/coin/${coin.id}/logo.png"),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .padding(8.dp)
                .background(Color.White, CircleShape),
            contentScale = ContentScale.Inside
        )
    }

}