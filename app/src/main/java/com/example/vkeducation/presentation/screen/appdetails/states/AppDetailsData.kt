package com.example.vkeducation.presentation.screen.appdetails.states

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducation.R
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.presentation.preview.PreviewData
import com.example.vkeducation.presentation.screen.appdetails.components.AppDescription
import com.example.vkeducation.presentation.screen.appdetails.components.AppDetailsHeader
import com.example.vkeducation.presentation.screen.appdetails.components.Developer
import com.example.vkeducation.presentation.screen.appdetails.components.InstallButton
import com.example.vkeducation.presentation.screen.appdetails.components.ScreenshotsList
import com.example.vkeducation.presentation.screen.appdetails.components.Showcase
import com.example.vkeducation.presentation.ui.theme.VKEducationTheme

@Composable
fun AppDetailsData(
    appDetails: AppDetails,
    showcaseApps: List<AppSummary>,
    onShowcaseAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val underDevelopmentText = stringResource(R.string.under_development)

    var descriptionCollapsed by rememberSaveable { mutableStateOf(false) }

    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(8.dp))
        AppDetailsHeader(
            appDetails = appDetails,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(16.dp))
        InstallButton(
            onClick = {
                Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(12.dp))
        ScreenshotsList(
            screenshotUrlList = appDetails.screenshotUrlList,
            contentPadding = PaddingValues(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        AppDescription(
            description = appDetails.description,
            collapsed = descriptionCollapsed,
            onReadMoreClick = {
                descriptionCollapsed = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Spacer(Modifier.height(12.dp))
        Developer(
            name = appDetails.developer,
            onClick = {
                Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Showcase(apps = showcaseApps, onAppClick = { onShowcaseAppClick() })
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppDetailsData() {
    VKEducationTheme {
        AppDetailsData(
            appDetails = PreviewData.appDetails,
            showcaseApps = PreviewData.apps,
            onShowcaseAppClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
