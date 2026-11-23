package com.example.vkeducation.presentation.screen.appdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.usecase.GetAppDetailsUseCase
import com.example.vkeducation.domain.usecase.GetAppsUseCase
import com.example.vkeducation.presentation.navigation.NavAppDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetails: GetAppDetailsUseCase,
    private val getApps: GetAppsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val appId = savedStateHandle.toRoute<NavAppDetails>().appId

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _effect = Channel<AppDetailsEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private var installJob: Job? = null

    init {
        loadingAppDetails()
    }

    fun onRepeat() {
        loadingAppDetails()
    }

    fun onInstallClick() {
        val current = _state.value
        if (current !is AppDetailsState.Data) return

        when (current.installState) {
            is InstallState.Installing -> {
                installJob?.cancel()
                installJob = null
                updateInstallState(InstallState.NotInstalled)
            }

            InstallState.NotInstalled -> {
                updateInstallState(InstallState.Installing(0))
                installJob = viewModelScope.launch {
                    emulateInstall()
                }
            }

            InstallState.Installed -> Unit
        }
    }

    private suspend fun emulateInstall() {
        for (progress in 5..100 step 5) {
            delay(150)
            updateInstallState(InstallState.Installing(progress))
        }
        updateInstallState(InstallState.Installed)
    }

    private fun updateInstallState(installState: InstallState) {
        _state.update { current ->
            if (current is AppDetailsState.Data) {
                current.copy(installState = installState)
            } else {
                current
            }
        }
    }

    private fun loadingAppDetails() {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            runCatching {
                coroutineScope {
                    val appDetails = async { getAppDetails(appId) }
                    val showcaseApps = async { getApps() }
                    _state.value = AppDetailsState.Data(
                        appDetails = appDetails.await(),
                        showcaseApps = showcaseApps.await()
                    )
                }
            }.onFailure {
                if (it is CancellationException) throw it
                _state.value = AppDetailsState.Error
            }
        }
    }

    fun onNavigateToApp(appId: String) {
        viewModelScope.launch {
            _effect.send(AppDetailsEffect.NavigateToApp(appId))
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
        val showcaseApps: List<AppSummary>,
        val installState: InstallState = InstallState.NotInstalled
    ) : AppDetailsState

    data object Error : AppDetailsState
}

sealed interface InstallState {
    data object NotInstalled : InstallState
    data class Installing(val progress: Int) : InstallState
    data object Installed : InstallState
}

sealed interface AppDetailsEffect {
    data object NavigateToAppList : AppDetailsEffect
    data class NavigateToApp(val appId: String) : AppDetailsEffect
}
