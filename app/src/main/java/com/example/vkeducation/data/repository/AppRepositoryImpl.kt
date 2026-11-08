package com.example.vkeducation.data.repository

import com.example.vkeducation.data.remote.api.AppApiService
import com.example.vkeducation.data.remote.mapper.toEntities
import com.example.vkeducation.data.remote.mapper.toEntity
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val appApiService: AppApiService
) : AppRepository {
    override suspend fun getApps(): List<AppSummary> {
        return appApiService.getApps().toEntities()
    }

    override suspend fun getAppDetails(appId: String): AppDetails {
        return appApiService.getAppDetails(appId).toEntity()
    }
}