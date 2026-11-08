package com.example.vkeducation.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppSummaryDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String
)