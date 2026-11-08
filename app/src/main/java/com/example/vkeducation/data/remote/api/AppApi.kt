package com.example.vkeducation.data.remote.api

import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto

interface AppApi {
    suspend fun getApps(): List<AppSummaryDto>

    suspend fun getAppDetails(): AppDetailsDto
}