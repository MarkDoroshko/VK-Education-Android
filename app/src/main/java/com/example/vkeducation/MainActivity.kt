package com.example.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducation.components.AppDetailItem
import com.example.vkeducation.components.ToolBar
import com.example.vkeducation.ui.theme.VKEducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKEducationTheme {
                AppDetailsScreen(appDetails = appDetails)
            }
        }
    }
}

@Composable
fun AppDetailsScreen(
    appDetails: List<AppDetail>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { ToolBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            appDetails.forEach { appDetail ->
                AppDetailItem(appDetail = appDetail)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppDetailsScreenPreview() {
    VKEducationTheme {
        AppDetailsScreen(appDetails = appDetails)
    }
}
