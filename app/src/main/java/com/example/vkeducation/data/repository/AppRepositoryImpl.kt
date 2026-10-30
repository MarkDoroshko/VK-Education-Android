package com.example.vkeducation.data.repository

import com.example.vkeducation.data.remote.api.AppApi
import com.example.vkeducation.data.remote.mapper.toEntities
import com.example.vkeducation.data.remote.mapper.toEntity
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.repository.AppRepository

class AppRepositoryImpl(
    private val appApi: AppApi
) : AppRepository {
    override suspend fun getApps(): List<AppSummary> {
        return appApi.getApps().toEntities()
    }

    override suspend fun getAppDetails(): AppDetails {
        return appApi.getAppDetails().toEntity()
    }
}