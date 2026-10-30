package com.example.vkeducation.di

import com.example.vkeducation.data.remote.api.AppApi
import com.example.vkeducation.data.remote.api.AppApiImpl
import com.example.vkeducation.data.repository.AppRepositoryImpl
import com.example.vkeducation.domain.repository.AppRepository

object AppContainer {
    private val appApi: AppApi by lazy { AppApiImpl() }

    val appRepository: AppRepository by lazy { AppRepositoryImpl(appApi) }
}