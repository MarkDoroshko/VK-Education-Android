package com.example.vkeducation.domain.usecase

import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.entity.Category
import com.example.vkeducation.domain.repository.AppRepository
import java.io.IOException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class AppUseCasesTest {
    private val repository = FakeAppRepository()
    private val getApps = GetAppsUseCase(repository)
    private val getAppDetails = GetAppDetailsUseCase(repository)

    @Test
    fun getAppsReturnsCatalogFromRepository() = runTest {
        val apps = listOf(
            AppSummary("game-1", "Chess", "Board game", Category.GAME),
            AppSummary("study-1", "Flashcards", "Study guide", Category.LEARNING)
        )
        repository.apps = apps

        assertEquals(apps, getApps())
    }

    @Test
    fun getAppsReturnsEmptyCatalog() = runTest {
        assertTrue(getApps().isEmpty())
    }

    @Test
    fun getAppsPropagatesRepositoryError() = runTest {
        val error = IOException("Catalog unavailable")
        repository.error = error

        val result = runCatching { getApps() }

        assertSame(error, result.exceptionOrNull())
    }

    @Test
    fun getAppDetailsReturnsDetailsForRequestedId() = runTest {
        val details = AppDetails(
            id = "game-2",
            name = "Checkers",
            developer = "Board Games Studio",
            category = Category.GAME,
            ageRating = 6,
            size = 12f,
            iconUrl = "checkers.png",
            screenshotUrlList = emptyList(),
            description = "Board game"
        )
        repository.detailsById = mapOf(details.id to details)

        assertEquals(details, getAppDetails("game-2"))
    }

    @Test
    fun getAppDetailsPropagatesRepositoryError() = runTest {
        val error = IOException("Details unavailable")
        repository.error = error

        val result = runCatching { getAppDetails("game-2") }

        assertSame(error, result.exceptionOrNull())
    }

    private class FakeAppRepository : AppRepository {
        var apps: List<AppSummary> = emptyList()
        var detailsById: Map<String, AppDetails> = emptyMap()
        var error: IOException? = null

        override suspend fun getApps(): List<AppSummary> {
            error?.let { throw it }
            return apps
        }

        override suspend fun getAppDetails(appId: String): AppDetails {
            error?.let { throw it }
            return detailsById.getValue(appId)
        }

        override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> =
            emptyMap()

        override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> =
            emptyMap()
    }
}
