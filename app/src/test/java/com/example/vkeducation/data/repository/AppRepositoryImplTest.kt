package com.example.vkeducation.data.repository

import com.example.vkeducation.data.remote.api.AppApiImpl
import com.example.vkeducation.data.remote.api.AppApiService
import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.entity.Category
import java.io.IOException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class AppRepositoryImplTest {
    private val apiService = FakeAppApiService()
    private val repository = AppRepositoryImpl(AppApiImpl(apiService))

    @Test
    fun getAppsReturnsMappedApps() = runTest {
        apiService.apps = listOf(AppSummaryDto("game-1", "Chess", "Board game", "game", "icon.png"))

        val result = repository.getApps()

        assertEquals(
            listOf(AppSummary("game-1", "Chess", "Board game", Category.GAME, "icon.png")),
            result
        )
    }

    @Test
    fun getAppsReturnsEmptyListWhenCatalogIsEmpty() = runTest {
        val result = repository.getApps()

        assertTrue(result.isEmpty())
    }

    @Test
    fun getAppsPropagatesApiError() = runTest {
        val error = IOException("Catalog unavailable")
        apiService.error = error

        val result = runCatching { repository.getApps() }

        assertSame(error, result.exceptionOrNull())
    }

    @Test
    fun getAppDetailsUsesRequestedIdAndReturnsMappedDetails() = runTest {
        val result = repository.getAppDetails("game-2")

        assertEquals("game-2", apiService.requestedAppId)
        assertEquals("game-2", result.id)
        assertEquals("Chess Studio", result.developer)
        assertEquals(Category.GAME, result.category)
        assertEquals(listOf("board.png"), result.screenshotUrlList)
    }

    @Test
    fun getAppDetailsPropagatesApiError() = runTest {
        val error = IOException("Details unavailable")
        apiService.error = error

        val result = runCatching { repository.getAppDetails("game-2") }

        assertSame(error, result.exceptionOrNull())
    }

    @Test
    fun loadRatingsSequentiallyReturnsRatingForEveryApp() = runTest {
        val ids = listOf("game-1", "game-2")

        val result = repository.loadRatingsSequentially(ids)

        assertEquals(ids.toSet(), result.keys)
        assertTrue(result.values.all { it in 3f..5f })
    }

    @Test
    fun loadRatingsInParallelReturnsRatingForEveryApp() = runTest {
        val ids = listOf("game-1", "game-2")

        val result = repository.loadRatingsInParallel(ids)

        assertEquals(ids.toSet(), result.keys)
        assertTrue(result.values.all { it in 3f..5f })
    }

    private class FakeAppApiService : AppApiService {
        var apps: List<AppSummaryDto> = emptyList()
        var error: IOException? = null
        var requestedAppId: String? = null

        override suspend fun getApps(): List<AppSummaryDto> {
            error?.let { throw it }
            return apps
        }

        override suspend fun getAppDetails(appId: String): AppDetailsDto {
            requestedAppId = appId
            error?.let { throw it }
            return AppDetailsDto(
                id = appId,
                name = "Chess",
                developer = "Chess Studio",
                category = "game",
                ageRating = 6,
                size = 24f,
                iconUrl = "icon.png",
                screenshotUrlList = listOf("board.png"),
                description = "Board game"
            )
        }
    }
}
