package com.example.vkeducation.presentation.screen.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducation.data.remote.api.AppApiImpl
import com.example.vkeducation.data.repository.AppRepositoryImpl
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.repository.AppRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppDetailsViewModel : ViewModel() {
    private val appRepository: AppRepository = AppRepositoryImpl(AppApiImpl())

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _effect = Channel<AppDetailsEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        loadingAppDetails()
    }

    fun onRepeat() {
        loadingAppDetails()
    }

    private fun loadingAppDetails() {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            runCatching {
                coroutineScope {
                    val appDetails = async { appRepository.getAppDetails() }
                    val showcaseApps = async { appRepository.getApps() }
                    _state.value = AppDetailsState.Data(
                        appDetails = appDetails.await(),
                        showcaseApps = showcaseApps.await()
                    )
                }
            }.onFailure {
                _state.value = AppDetailsState.Error
            }
        }
    }

    fun onNavigateToAppList() {
        viewModelScope.launch {
            _effect.send(AppDetailsEffect.NavigateToAppList)
        }
    }
}

sealed interface AppDetailsState {
    data object Loading : AppDetailsState
    data class Data(
        val appDetails: AppDetails,
        val showcaseApps: List<AppSummary>
    ) : AppDetailsState
    data object Error : AppDetailsState
}

sealed interface AppDetailsEffect {
    data object NavigateToAppList : AppDetailsEffect
}