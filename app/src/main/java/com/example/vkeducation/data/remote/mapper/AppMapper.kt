package com.example.vkeducation.data.remote.mapper

import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.entity.Category

fun AppSummaryDto.toEntity(): AppSummary {
    return AppSummary(
        id = this.id,
        name = this.name,
        description = this.description,
        category = this.category.toCategory(),
        iconUrl = this.iconUrl
    )
}

fun List<AppSummaryDto>.toEntities(): List<AppSummary> {
    return this.map { it.toEntity() }
}

fun AppDetailsDto.toEntity(): AppDetails {
    return AppDetails(
        id = this.id,
        name = this.name,
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
    "game", "игры" -> Category.GAME
    "learning", "образование" -> Category.LEARNING
    "purchases", "шопинг" -> Category.PURCHASES
    "производительность" -> Category.PRODUCTIVITY
    "здоровье и фитнес" -> Category.HEALTH_AND_FITNESS
    "фото и видео" -> Category.PHOTO_AND_VIDEO
    "еда и напитки" -> Category.FOOD_AND_DRINK
    "образ жизни" -> Category.LIFESTYLE
    "новости" -> Category.NEWS
    "музыка" -> Category.MUSIC
    "финансы" -> Category.FINANCE
    "утилиты" -> Category.UTILITIES
    "навигация" -> Category.NAVIGATION
    "общение" -> Category.COMMUNICATION
    "бизнес" -> Category.BUSINESS
    "погода" -> Category.WEATHER
    "развлечения" -> Category.ENTERTAINMENT
    "книги и справочники" -> Category.BOOKS_AND_REFERENCE
    else -> Category.OTHER
}
