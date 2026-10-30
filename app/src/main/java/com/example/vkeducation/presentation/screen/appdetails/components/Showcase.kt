package com.example.vkeducation.presentation.screen.appdetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducation.R
import com.example.vkeducation.presentation.screen.applist.components.AppItem
import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.entity.Category
import com.example.vkeducation.presentation.ui.theme.VKEducationTheme

@Composable
fun Showcase(
    apps: List<AppSummary>,
    onAppClick: (AppSummary) -> Unit,
    modifier: Modifier = Modifier
) {
    val pages = remember(apps) { apps.chunked(3) }
    val pagerState = rememberPagerState { pages.size }

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.app_details_showcase),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(8.dp))
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 16.dp,
            verticalAlignment = Alignment.Top,
        ) { page ->
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                pages[page].forEach { app ->
                    AppItem(app = app, onClick = { onAppClick(app) })
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewShowcase() {
    VKEducationTheme {
        Showcase(
            apps = listOf(
                AppSummary(
                    id = "app_summary_1",
                    name = "Космические гонки",
                    description = "Аркадные гонки на космических кораблях с друзьями и рейтингом игроков",
                    category = Category.GAME
                ),
                AppSummary(
                    id = "app_summary_2",
                    name = "Слово за слово",
                    description = "Головоломка: составляйте слова из букв и проходите сотни уровней",
                    category = Category.GAME
                ),
                AppSummary(
                    id = "app_summary_3",
                    name = "Шахматный клуб",
                    description = "Играйте онлайн, решайте задачи и разбирайте партии гроссмейстеров",
                    category = Category.GAME
                ),
                AppSummary(
                    id = "app_summary_4",
                    name = "Английский каждый день",
                    description = "Короткие уроки по 10 минут, карточки слов и тренировка произношения",
                    category = Category.LEARNING
                ),
                AppSummary(
                    id = "app_summary_5",
                    name = "Алгоритмика",
                    description = "Интерактивный курс по алгоритмам и структурам данных с задачами",
                    category = Category.LEARNING
                ),
                AppSummary(
                    id = "app_summary_6",
                    name = "Математика 5–11",
                    description = "Теория, видеоразборы и тесты по школьной программе",
                    category = Category.LEARNING
                ),
                AppSummary(
                    id = "app_summary_7",
                    name = "Маркет у дома",
                    description = "Доставка продуктов из ближайших магазинов за 30 минут",
                    category = Category.PURCHASES
                ),
                AppSummary(
                    id = "app_summary_8",
                    name = "Скидочник",
                    description = "Все акции и промокоды любимых магазинов в одном месте",
                    category = Category.PURCHASES
                ),
                AppSummary(
                    id = "app_summary_9",
                    name = "Барахолка",
                    description = "Покупайте и продавайте вещи с рук рядом с домом",
                    category = Category.PURCHASES
                )
            ),
            onAppClick = {}
        )
    }
}