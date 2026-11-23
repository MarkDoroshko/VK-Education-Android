package com.example.vkeducation.data.remote.api

import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class AppApiImpl @Inject constructor(
    private val appApiService: AppApiService
) {
    suspend fun getApps(): List<AppSummaryDto> {
        return appApiService.getApps()
    }

    suspend fun getAppDetails(appId: String): AppDetailsDto {
        return appApiService.getAppDetails(appId)
    }

    suspend fun getRating(id: String): Float {
        delay(1500)
        return (30..50).random() / 10f
    }
}
