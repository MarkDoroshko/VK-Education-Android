package com.example.vkeducation.data.remote.dto

data class AppDetailsDto(
    val id: String,
    val title: String,
    val developer: String,
    val category: String,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>,
    val description: String
)