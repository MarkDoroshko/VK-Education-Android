package com.example.vkeducation.domain.usecase

import com.example.vkeducation.domain.entity.AppSummary
import com.example.vkeducation.domain.repository.AppRepository
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<AppSummary> {
        return repository.getApps()
    }
}
