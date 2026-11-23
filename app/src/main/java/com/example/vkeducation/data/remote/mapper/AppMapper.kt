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

fun AppSummary.toDto(): AppSummaryDto {
    return AppSummaryDto(
        id = id,
        name = name,
        description = description,
        category = categoryNames.getValue(category),
        iconUrl = iconUrl
    )
}

fun AppDetails.toDto(): AppDetailsDto {
    return AppDetailsDto(
        id = id,
        name = name,
        developer = developer,
        category = categoryNames.getValue(category),
        ageRating = ageRating,
        size = size,
        iconUrl = iconUrl,
        screenshotUrlList = screenshotUrlList,
        description = description
    )
}

private val categoryNames = mapOf(
    Category.GAME to "game",
    Category.LEARNING to "learning",
    Category.PURCHASES to "purchases",
    Category.PRODUCTIVITY to "производительность",
    Category.HEALTH_AND_FITNESS to "здоровье и фитнес",
    Category.PHOTO_AND_VIDEO to "фото и видео",
    Category.FOOD_AND_DRINK to "еда и напитки",
    Category.LIFESTYLE to "образ жизни",
    Category.NEWS to "новости",
    Category.MUSIC to "музыка",
    Category.FINANCE to "финансы",
    Category.UTILITIES to "утилиты",
    Category.NAVIGATION to "навигация",
    Category.COMMUNICATION to "общение",
    Category.BUSINESS to "бизнес",
    Category.WEATHER to "погода",
    Category.ENTERTAINMENT to "развлечения",
    Category.BOOKS_AND_REFERENCE to "книги и справочники",
    Category.OTHER to "other"
)

private val categoriesByName = categoryNames.entries.associate { (category, name) ->
    name to category
} + mapOf(
    "игры" to Category.GAME,
    "образование" to Category.LEARNING,
    "шопинг" to Category.PURCHASES
)

private fun String.toCategory(): Category = categoriesByName[lowercase()] ?: Category.OTHER
