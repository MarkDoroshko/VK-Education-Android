package com.example.vkeducation.domain.repository

import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary

interface AppRepository {
    suspend fun getApps(): List<AppSummary>

    suspend fun getAppDetails(): AppDetails
}