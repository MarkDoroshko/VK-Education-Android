package com.example.vkeducation.applist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.vkeducation.components.rememberShimmerBrush
import com.example.vkeducation.ui.theme.VKEducationTheme

private const val PLACEHOLDER_COUNT = 6

@Composable
fun AppListLoading(
    modifier: Modifier = Modifier
) {
    val brush = rememberShimmerBrush()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        repeat(PLACEHOLDER_COUNT) {
            AppDetailItemPlaceholder(brush = brush)
        }
    }
}

// Повторяет форму AppDetailItem: иконка, категория, название и две строки описания
@Composable
private fun AppDetailItemPlaceholder(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(brush = brush, shape = RoundedCornerShape(14.dp))
            )

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                PlaceholderLine(brush = brush, height = 10.dp, widthFraction = 0.25f)
                PlaceholderLine(brush = brush, height = 22.dp, widthFraction = 0.6f)
                PlaceholderLine(brush = brush, height = 14.dp, widthFraction = 1f)
                PlaceholderLine(brush = brush, height = 14.dp, widthFraction = 0.8f)
            }
        }
    }
}

@Composable
private fun PlaceholderLine(
    brush: Brush,
    height: Dp,
    widthFraction: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(widthFraction)
            .height(height)
            .background(brush = brush, shape = RoundedCornerShape(4.dp))
    )
}

@Preview(showBackground = true)
@Composable
fun AppListLoadingPreview() {
    VKEducationTheme {
        AppListLoading(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
