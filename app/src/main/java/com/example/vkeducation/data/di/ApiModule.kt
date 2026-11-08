package com.example.vkeducation.data.di

import com.example.vkeducation.data.remote.api.AppApi
import com.example.vkeducation.data.remote.api.AppApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ApiModule {
    @Binds
    fun bindAppApi(impl: AppApiImpl): AppApi
}