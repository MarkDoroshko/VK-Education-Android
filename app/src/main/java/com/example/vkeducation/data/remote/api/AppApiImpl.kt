package com.example.vkeducation.data.remote.api

import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApiService {
    @GET("catalog")
    suspend fun getApps(): List<AppSummaryDto>

    @GET("catalog/{appId}")
    suspend fun getAppDetails(@Path("appId") appId: String): AppDetailsDto
}