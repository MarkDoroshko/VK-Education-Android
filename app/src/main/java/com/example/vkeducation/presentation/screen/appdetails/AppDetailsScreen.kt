package com.example.vkeducation.presentation.screen.appdetails

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkeducation.R
import com.example.vkeducation.di.AppContainer
import com.example.vkeducation.presentation.components.ErrorState
import com.example.vkeducation.presentation.preview.PreviewData
import com.example.vkeducation.presentation.screen.appdetails.components.Toolbar
import com.example.vkeducation.presentation.screen.appdetails.states.AppDetailsData
import com.example.vkeducation.presentation.screen.appdetails.states.AppDetailsLoading
import com.example.vkeducation.presentation.ui.theme.VKEducationTheme

@Composable
fun AppDetailsRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel { AppDetailsViewModel(AppContainer.appRepository) }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                AppDetailsEffect.NavigateToAppList -> onBackClick()
            }
        }
    }

    AppDetailsScreen(
        state = state,
        onBackClick = viewModel::onNavigateToAppList,
        onShowcaseAppClick = viewModel::onNavigateToAppList,
        onRepeat = viewModel::onRepeat,
        modifier = modifier
    )
}

@Composable
fun AppDetailsScreen(
    state: AppDetailsState,
    onBackClick: () -> Unit,
    onShowcaseAppClick: () -> Unit,
    onRepeat: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val underDevelopmentText = stringResource(R.string.under_development)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                onBackClick = onBackClick,
                onShareClick = {
                    Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state) {
                AppDetailsState.Loading -> AppDetailsLoading(modifier = Modifier.fillMaxSize())

                is AppDetailsState.Data -> AppDetailsData(
                    appDetails = state.appDetails,
                    showcaseApps = state.showcaseApps,
                    onShowcaseAppClick = onShowcaseAppClick,
                    modifier = Modifier.fillMaxSize()
                )

                AppDetailsState.Error -> ErrorState(
                    onRepeat = onRepeat,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VKEducationTheme {
        AppDetailsScreen(
            state = AppDetailsState.Data(
                appDetails = PreviewData.appDetails,
                showcaseApps = PreviewData.apps
            ),
            onBackClick = {},
            onShowcaseAppClick = {},
            onRepeat = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
