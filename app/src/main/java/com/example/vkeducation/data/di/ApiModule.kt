package com.example.vkeducation.data.di

import com.example.vkeducation.data.remote.api.AppApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Singleton
    @Provides
    fun provideConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun provideRetrofit(converterFactory: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://185.103.109.134/")
            .addConverterFactory(converterFactory)
            .build()

    @Singleton
    @Provides
    fun provideAppApiService(retrofit: Retrofit): AppApiService =
        retrofit.create(AppApiService::class.java)
}
