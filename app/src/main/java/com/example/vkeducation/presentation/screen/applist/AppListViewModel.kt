package com.example.vkeducation.presentation.screen.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.data.remote.api.AppApiImpl
import com.example.vkeducation.data.repository.AppRepositoryImpl
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.repository.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {
    private val appRepository: AppRepository = AppRepositoryImpl(AppApiImpl())

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _effect = Channel<AppListEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        loadingApps()
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

    fun onRepeat() {
        loadingApps()
    }

    private fun loadingApps() {
        viewModelScope.launch {
            _state.value = AppListState.Loading
            runCatching {
                val apps = appRepository.getApps()
                _state.value = AppListState.Data(apps = apps)
            }.onFailure {
                _state.value = AppListState.Error
            }
        }
    }
}

sealed interface AppListState {
    data object Loading : AppListState
    data class Data(val apps: List<AppSummary>) : AppListState
    data object Error : AppListState
}

sealed interface AppListEffect {
    data object NavigateToApp : AppListEffect
    data object ShowTestSnackbar : AppListEffect
}