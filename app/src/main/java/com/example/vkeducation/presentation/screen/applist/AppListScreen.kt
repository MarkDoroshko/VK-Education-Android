package com.example.vkeducation.presentation.screen.applist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkeducation.R
import com.example.vkeducation.di.AppContainer
import com.example.vkeducation.presentation.components.ErrorState
import com.example.vkeducation.presentation.preview.PreviewData
import com.example.vkeducation.presentation.screen.applist.components.ToolBar
import com.example.vkeducation.presentation.screen.applist.states.AppListData
import com.example.vkeducation.presentation.screen.applist.states.AppListLoading
import com.example.vkeducation.presentation.ui.theme.VKEducationTheme
import kotlinx.coroutines.launch

@Composable
fun AppListRoute(
    onAppClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel { AppListViewModel(AppContainer.appRepository) }
    val state by viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = stringResource(R.string.app_list_toolbar_snackbar)
    val currentOnAppClick by rememberUpdatedState(onAppClick)

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                AppListEffect.NavigateToApp -> currentOnAppClick()
                AppListEffect.ShowTestSnackbar -> launch {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }
    }

    AppListScreen(
        state = state,
        snackbarHostState = snackbarHostState,
        onAppClick = viewModel::onNavigateToApp,
        onToolbarClick = viewModel::onToolbarClick,
        onRepeat = viewModel::onRepeat,
        modifier = modifier
    )
}

@Composable
fun AppListScreen(
    state: AppListState,
    snackbarHostState: SnackbarHostState,
    onAppClick: () -> Unit,
    onToolbarClick: () -> Unit,
    onRepeat: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { ToolBar(onClick = onToolbarClick) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            when (state) {
                AppListState.Loading -> AppListLoading(modifier = Modifier.fillMaxSize())

                is AppListState.Data -> AppListData(
                    apps = state.apps,
                    onAppClick = onAppClick,
                    modifier = Modifier.fillMaxSize()
                )

                AppListState.Error -> ErrorState(
                    onRepeat = onRepeat,
                    modifier = modifier.fillMaxSize()
                )
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.align(Alignment.TopCenter)
            ) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError,
                    shape = MaterialTheme.shapes.medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppListScreenPreview() {
    VKEducationTheme {
        AppListScreen(
            state = AppListState.Data(apps = PreviewData.apps),
            snackbarHostState = remember { SnackbarHostState() },
            onAppClick = {},
            onToolbarClick = {},
            onRepeat = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppListScreenLoadingPreview() {
    VKEducationTheme {
        AppListScreen(
            state = AppListState.Loading,
            snackbarHostState = remember { SnackbarHostState() },
            onAppClick = {},
            onToolbarClick = {},
            onRepeat = {}
        )
    }
}
