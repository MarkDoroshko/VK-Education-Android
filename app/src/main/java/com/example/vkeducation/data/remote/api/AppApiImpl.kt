package com.example.vkeducation.data.remote.api

import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class AppApiImpl : AppApi {
    override suspend fun getApps(): List<AppSummaryDto> {
        delay(2.seconds)
        return MockAppData.appSummaries
    }

    override suspend fun getAppDetails(): AppDetailsDto {
        delay(2.seconds)
        return MockAppData.appDetails
    }

}