package com.example.vkeducation

enum class AppCategory { GAME, LEARNING, PURCHASES }

data class AppDetail(
    val title: String,
    val description: String,
    val category: AppCategory
)