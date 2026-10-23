package com.example.vkeducation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

private const val SHIMMER_WIDTH = 400f
private const val SHIMMER_DISTANCE = 1400f
private const val SHIMMER_DURATION_MS = 1200

@Composable
fun rememberShimmerBrush(): Brush {
    val baseColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.10f)
    val highlightColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.03f)

    val transition = rememberInfiniteTransition(label = "shimmer")
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = SHIMMER_DISTANCE,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = SHIMMER_DURATION_MS, easing = LinearEasing)
        ),
        label = "shimmerOffset"
    )

    return Brush.linearGradient(
        colors = listOf(baseColor, highlightColor, baseColor),
        start = Offset(offset - SHIMMER_WIDTH, offset - SHIMMER_WIDTH),
        end = Offset(offset, offset)
    )
}
