package com.example.vkeducation.presentation.screen.applist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _effect = Channel<AppListEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        loadingApps()
    }

    fun onNavigateToApp(appId: String) {
        viewModelScope.launch {
            _effect.send(AppListEffect.NavigateToApp(appId))
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

                val ids = apps.map { it.id }
                val sequentialRatings: Map<String, Float>
                val sequentialTime = measureTimeMillis {
                    sequentialRatings = appRepository.loadRatingsSequentially(ids)
                }
                _state.value = AppListState.Data(apps = apps, ratings = sequentialRatings)
                Log.d("AppRatings", "Sequential: ${ids.size} apps, $sequentialTime ms")

                val parallelRatings: Map<String, Float>
                val parallelTime = measureTimeMillis {
                    parallelRatings = appRepository.loadRatingsInParallel(ids)
                }
                _state.value = AppListState.Data(apps = apps, ratings = parallelRatings)
                Log.d("AppRatings", "Parallel: ${ids.size} apps, $parallelTime ms")
            }.onFailure {
                if (it is CancellationException) throw it
                _state.value = AppListState.Error
            }
        }
    }
}

sealed interface AppListState {
    data object Loading : AppListState
    data class Data(
        val apps: List<AppSummary>,
        val ratings: Map<String, Float> = emptyMap()
    ) : AppListState
    data object Error : AppListState
}

sealed interface AppListEffect {
    data class NavigateToApp(val appId: String) : AppListEffect
    data object ShowTestSnackbar : AppListEffect
}
