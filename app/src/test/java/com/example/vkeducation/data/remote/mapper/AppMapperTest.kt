package com.example.vkeducation.data.remote.mapper

import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto
import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.entity.Category
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppMapperTest {
    private val summaryDto = AppSummaryDto(
        id = "game-1",
        name = "Chess",
        description = "Board game",
        category = "game",
        iconUrl = "icon.png"
    )
    private val summary = AppSummary(
        id = "game-1",
        name = "Chess",
        description = "Board game",
        category = Category.GAME,
        iconUrl = "icon.png"
    )
    private val detailsDto = AppDetailsDto(
        id = "weather-1",
        name = "Forecast",
        developer = "Weather Studio",
        category = "погода",
        ageRating = 0,
        size = 15.5f,
        iconUrl = "weather.png",
        screenshotUrlList = listOf("today.png", "week.png"),
        description = "Weather forecast"
    )
    private val details = AppDetails(
        id = "weather-1",
        name = "Forecast",
        developer = "Weather Studio",
        category = Category.WEATHER,
        ageRating = 0,
        size = 15.5f,
        iconUrl = "weather.png",
        screenshotUrlList = listOf("today.png", "week.png"),
        description = "Weather forecast"
    )

    @Test
    fun summaryDtoMapsAllFieldsToDomain() {
        assertEquals(summary, summaryDto.toEntity())
    }

    @Test
    fun detailsDtoMapsAllFieldsToDomain() {
        assertEquals(details, detailsDto.toEntity())
    }

    @Test
    fun dtoCategoryAcceptsRussianLabelWithoutMatchingCase() {
        val dto = summaryDto.copy(category = "ОБРАЗОВАНИЕ")

        assertEquals(Category.LEARNING, dto.toEntity().category)
    }

    @Test
    fun unknownDtoCategoryMapsToOther() {
        val dto = summaryDto.copy(category = "unknown")

        assertEquals(Category.OTHER, dto.toEntity().category)
    }

    @Test
    fun summaryDtoListKeepsOrder() {
        val secondDto = summaryDto.copy(id = "shop-1", name = "Shop", category = "purchases")
        val secondSummary = summary.copy(id = "shop-1", name = "Shop", category = Category.PURCHASES)

        assertEquals(listOf(summary, secondSummary), listOf(summaryDto, secondDto).toEntities())
    }

    @Test
    fun emptySummaryDtoListStaysEmpty() {
        assertTrue(emptyList<AppSummaryDto>().toEntities().isEmpty())
    }

    @Test
    fun summaryMapsAllFieldsToDto() {
        assertEquals(summaryDto, summary.toDto())
    }

    @Test
    fun detailsMapAllFieldsToDto() {
        assertEquals(detailsDto, details.toDto())
    }

    @Test
    fun otherCategoryUsesOtherLabelInDto() {
        val app = summary.copy(category = Category.OTHER)

        assertEquals("other", app.toDto().category)
    }

    @Test
    fun detailsDtoKeepsEmptyScreenshotList() {
        val app = details.copy(screenshotUrlList = emptyList())

        assertTrue(app.toDto().screenshotUrlList.isEmpty())
    }

    @Test
    fun everyDomainCategorySurvivesConversionToDtoAndBack() {
        for (category in Category.entries) {
            val app = summary.copy(category = category)

            assertEquals(category.name, app, app.toDto().toEntity())
        }
    }
}
