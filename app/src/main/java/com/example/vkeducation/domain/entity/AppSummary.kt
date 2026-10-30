package com.example.vkeducation.domain.entity

// Для списка и «Витрины»
data class AppSummary(
    val id: String,
    val name: String,
    val description: String,
    val category: Category
)