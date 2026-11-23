package com.example.vkeducation.data.repository

import com.example.vkeducation.data.remote.api.AppApiImpl
import com.example.vkeducation.data.remote.mapper.toEntities
import com.example.vkeducation.data.remote.mapper.toEntity
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.repository.AppRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val appApi: AppApiImpl
) : AppRepository {
    override suspend fun getApps(): List<AppSummary> {
        return appApi.getApps().toEntities()
    }

    override suspend fun getAppDetails(appId: String): AppDetails {
        return appApi.getAppDetails(appId).toEntity()
    }

    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        return ids.associateWith { appApi.getRating(it) }
    }

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> = coroutineScope {
        ids.map { id ->
            async { id to appApi.getRating(id) }
        }.awaitAll().toMap()
    }
}
