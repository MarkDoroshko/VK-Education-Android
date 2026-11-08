package com.example.vkeducation.presentation.screen.applist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.entity.Category
import com.example.vkeducation.presentation.screen.applist.mapper.labelRes
import com.example.vkeducation.presentation.ui.theme.VKEducationTheme

@Composable
fun AppItem(
    app: AppSummary,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
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
            AppIcon(iconUrl = app.iconUrl)

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(app.category.labelRes()),
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    lineHeight = 10.sp
                )

                Text(
                    text = app.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )

                Text(
                    text = app.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun AppIcon(
    iconUrl: String,
    modifier: Modifier = Modifier
) {
    var isLoading by remember(iconUrl) { mutableStateOf(iconUrl.isNotBlank()) }

    Box(
        modifier = modifier
            .size(56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        AsyncImage(
            model = iconUrl.takeIf { it.isNotBlank() },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
            onState = { state ->
                isLoading = iconUrl.isNotBlank() &&
                    (state is AsyncImagePainter.State.Empty || state is AsyncImagePainter.State.Loading)
            }
        )
        if (isLoading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(rememberShimmerBrush())
            )
        }
    }
}

@Preview
@Composable
fun AppItemPreview() {
    VKEducationTheme {
        AppItem(
            app = AppSummary(
                id = "test",
                name = "Test",
                description = "Test test test...",
                category = Category.GAME
            ),
            onClick = {}
        )
    }
}
