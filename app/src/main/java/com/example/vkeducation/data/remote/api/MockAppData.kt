package com.example.vkeducation.data.remote.api

import com.example.vkeducation.data.remote.dto.AppDetailsDto
import com.example.vkeducation.data.remote.dto.AppSummaryDto

internal object MockAppData {
    val appSummaries: List<AppSummaryDto> = listOf(
        AppSummaryDto(
            id = "app_summary_1",
            title = "Космические гонки",
            description = "Аркадные гонки на космических кораблях с друзьями и рейтингом игроков",
            category = "game"
        ),
        AppSummaryDto(
            id = "app_summary_2",
            title = "Слово за слово",
            description = "Головоломка: составляйте слова из букв и проходите сотни уровней",
            category = "game"
        ),
        AppSummaryDto(
            id = "app_summary_3",
            title = "Шахматный клуб",
            description = "Играйте онлайн, решайте задачи и разбирайте партии гроссмейстеров",
            category = "game"
        ),
        AppSummaryDto(
            id = "app_summary_4",
            title = "Английский каждый день",
            description = "Короткие уроки по 10 минут, карточки слов и тренировка произношения",
            category = "learning"
        ),
        AppSummaryDto(
            id = "app_summary_5",
            title = "Алгоритмика",
            description = "Интерактивный курс по алгоритмам и структурам данных с задачами",
            category = "learning"
        ),
        AppSummaryDto(
            id = "app_summary_6",
            title = "Математика 5–11",
            description = "Теория, видеоразборы и тесты по школьной программе",
            category = "learning"
        ),
        AppSummaryDto(
            id = "app_summary_7",
            title = "Маркет у дома",
            description = "Доставка продуктов из ближайших магазинов за 30 минут",
            category = "purchases"
        ),
        AppSummaryDto(
            id = "app_summary_8",
            title = "Скидочник",
            description = "Все акции и промокоды любимых магазинов в одном месте",
            category = "purchases"
        ),
        AppSummaryDto(
            id = "app_summary_9",
            title = "Барахолка",
            description = "Покупайте и продавайте вещи с рук рядом с домом",
            category = "purchases"
        )
    )

    val appDetails: AppDetailsDto = AppDetailsDto(
        id = "fa2e31b8-1234-4cf7-9914-108a170a1b01",
        title = "Гильдия Героев: Экшен ММО РПГ",
        developer = "VK Play",
        category = "game",
        ageRating = 12,
        size = 223.7f,
        iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
        screenshotUrlList = listOf(
            "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
            "https://static.rustore.ru/imgproxy/dZCvNtRKKFpzOmGlTxLszUPmwi661IhXynYZGsJQvLw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/60ec4cbc-dcf6-4e69-aa6f-cc2da7de1af6.jpg@webp",
            "https://static.rustore.ru/imgproxy/g5whSI1uNqaL2TUO7TFfM8M63vXpWXNCm2vlX4Ahvc4/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/c2dde8bc-c4ab-482a-80a5-2789149f598d.jpg@webp",
            "https://static.rustore.ru/imgproxy/TjeurtC7BczOVJt74XhjGYuQnG1l4rx6zpDqyMb00GY/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/08318f76-7a9c-43aa-b4a7-1aa878d00861.jpg@webp",
        ),
        description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!"
    )
}