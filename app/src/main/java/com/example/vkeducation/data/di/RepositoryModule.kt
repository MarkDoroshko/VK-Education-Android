package com.example.vkeducation.data.di

import com.example.vkeducation.data.repository.AppRepositoryImpl
import com.example.vkeducation.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}