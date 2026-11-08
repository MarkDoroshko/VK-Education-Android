package com.example.vkeducation.presentation.screen.applist.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.presentation.screen.applist.components.AppItem

@Composable
fun AppListData(
    apps: List<AppSummary>,
    onAppClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(apps, key = { it.id }) { app ->
            AppItem(app = app, onClick = { onAppClick(app.id) })
        }
    }
}
