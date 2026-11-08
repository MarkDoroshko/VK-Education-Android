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
}

fun Category.color(): Color = when (this) {
    Category.GAME -> CategoryGame
    Category.LEARNING -> CategoryLearning
    Category.PURCHASES -> CategoryPurchases
}
