package com.example.vkeducation.components

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.vkeducation.AppCategory
import com.example.vkeducation.R
import com.example.vkeducation.ui.theme.CategoryGame
import com.example.vkeducation.ui.theme.CategoryLearning
import com.example.vkeducation.ui.theme.CategoryPurchases

@StringRes
fun AppCategory.labelRes(): Int = when (this) {
    AppCategory.GAME -> R.string.category_game
    AppCategory.LEARNING -> R.string.category_learning
    AppCategory.PURCHASES -> R.string.category_purchases
}

fun AppCategory.color(): Color = when (this) {
    AppCategory.GAME -> CategoryGame
    AppCategory.LEARNING -> CategoryLearning
    AppCategory.PURCHASES -> CategoryPurchases
}
