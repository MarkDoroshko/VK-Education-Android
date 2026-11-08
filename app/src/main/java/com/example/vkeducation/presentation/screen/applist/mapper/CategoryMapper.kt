package com.example.vkeducation.presentation.screen.applist.mapper

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.vkeducation.R
import com.example.vkeducation.domain.entity.Category
import com.example.vkeducation.presentation.ui.theme.CategoryGame
import com.example.vkeducation.presentation.ui.theme.CategoryLearning
import com.example.vkeducation.presentation.ui.theme.CategoryPurchases

@StringRes
fun Category.labelRes(): Int = when (this) {
    Category.GAME -> R.string.category_game
    Category.LEARNING -> R.string.category_learning
    Category.PURCHASES -> R.string.category_purchases
    Category.PRODUCTIVITY -> R.string.category_productivity
    Category.HEALTH_AND_FITNESS -> R.string.category_health_and_fitness
    Category.PHOTO_AND_VIDEO -> R.string.category_photo_and_video
    Category.FOOD_AND_DRINK -> R.string.category_food_and_drink
    Category.LIFESTYLE -> R.string.category_lifestyle
    Category.NEWS -> R.string.category_news
    Category.MUSIC -> R.string.category_music
    Category.FINANCE -> R.string.category_finance
    Category.UTILITIES -> R.string.category_utilities
    Category.NAVIGATION -> R.string.category_navigation
    Category.COMMUNICATION -> R.string.category_communication
    Category.BUSINESS -> R.string.category_business
    Category.WEATHER -> R.string.category_weather
    Category.ENTERTAINMENT -> R.string.category_entertainment
    Category.BOOKS_AND_REFERENCE -> R.string.category_books_and_reference
    Category.OTHER -> R.string.category_other
}

fun Category.color(): Color = when (this) {
    Category.GAME -> CategoryGame
    Category.LEARNING -> CategoryLearning
    Category.PURCHASES -> CategoryPurchases
    Category.PRODUCTIVITY,
    Category.HEALTH_AND_FITNESS,
    Category.PHOTO_AND_VIDEO,
    Category.FOOD_AND_DRINK,
    Category.LIFESTYLE,
    Category.NEWS,
    Category.MUSIC,
    Category.FINANCE,
    Category.UTILITIES,
    Category.NAVIGATION,
    Category.COMMUNICATION,
    Category.BUSINESS,
    Category.WEATHER,
    Category.ENTERTAINMENT,
    Category.BOOKS_AND_REFERENCE,
    Category.OTHER -> Color.Gray
}
