package com.example.vkeducation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.AppDetail
import com.example.vkeducation.appsDetails
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class AppListViewModel : ViewModel() {
    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _effect = Channel<AppListEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        loadingAppsDetails()
    }

    fun onNavigateToApp() {
        viewModelScope.launch {
            _effect.send(AppListEffect.NavigateToApp)
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _effect.send(AppListEffect.ShowTestSnackbar)
        }
    }

    private fun loadingAppsDetails() {
        viewModelScope.launch {
            delay(2.seconds)
            _state.value = AppListState.Data(appsDetails = appsDetails)
        }
    }
}

sealed interface AppListState {
    data object Loading : AppListState
    data class Data(val appsDetails: List<AppDetail>) : AppListState
}

sealed interface AppListEffect {
    data object NavigateToApp : AppListEffect
    data object ShowTestSnackbar : AppListEffect
}