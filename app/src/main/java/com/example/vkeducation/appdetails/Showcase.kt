package com.example.vkeducation.appdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducation.AppDetail
import com.example.vkeducation.R
import com.example.vkeducation.appsDetails
import com.example.vkeducation.components.AppDetailItem
import com.example.vkeducation.ui.theme.VKEducationTheme

@Composable
fun Showcase(
    apps: List<AppDetail>,
    onAppClick: (AppDetail) -> Unit,
    modifier: Modifier = Modifier
) {
    val pages = remember(apps) { apps.chunked(3) }
    val pagerState = rememberPagerState { pages.size }

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.app_details_showcase),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(8.dp))
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 16.dp,
            verticalAlignment = Alignment.Top,
        ) { page ->
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                pages[page].forEach { app ->
                    AppDetailItem(appDetail = app, onClick = { onAppClick(app) })
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewShowcase() {
    VKEducationTheme {
        Showcase(
            apps = appsDetails,
            onAppClick = {}
        )
    }
}