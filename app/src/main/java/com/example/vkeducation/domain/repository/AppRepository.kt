package com.example.vkeducation.domain.repository

import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary

interface AppRepository {
    suspend fun getApps(): List<AppSummary>

    suspend fun getAppDetails(appId: String): AppDetails

    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float>

    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float>
}
