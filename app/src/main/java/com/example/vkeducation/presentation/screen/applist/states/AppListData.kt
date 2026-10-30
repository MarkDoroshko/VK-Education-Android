package com.example.vkeducation.presentation.screen.applist.states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.presentation.screen.applist.components.AppItem

@Composable
fun AppListData(
    apps: List<AppSummary>,
    onAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        apps.forEach { app ->
            AppItem(app = app, onClick = onAppClick)
        }
    }
}