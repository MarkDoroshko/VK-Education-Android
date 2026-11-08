package com.example.vkeducation.data.remote.mapper

import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.entity.Category

fun AppSummaryDto.toEntity(): AppSummary {
    return AppSummary(
        id = this.id,
        name = this.title,
        description = this.description,
        category = this.category.toCategory()
    )
}

fun List<AppSummaryDto>.toEntities(): List<AppSummary> {
    return this.map { it.toEntity() }
}

fun AppDetailsDto.toEntity(): AppDetails {
    return AppDetails(
        id = this.id,
        name = this.title,
        developer = this.developer,
        category = this.category.toCategory(),
        ageRating = this.ageRating,
        size = this.size,
        iconUrl = this.iconUrl,
        screenshotUrlList = this.screenshotUrlList,
        description = this.description
    )
}

private fun String.toCategory(): Category = when (this.lowercase()) {
    "game" -> Category.GAME
    "learning" -> Category.LEARNING
    "purchases" -> Category.PURCHASES
    else -> error("Unknown category: $this")
}