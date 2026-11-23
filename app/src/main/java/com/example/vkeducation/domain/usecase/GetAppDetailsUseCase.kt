package com.example.vkeducation.domain.usecase

import com.example.vkeducation.domain.entity.AppDetails
import com.example.vkeducation.domain.repository.AppRepository
import javax.inject.Inject

class GetAppDetailsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(appId: String): AppDetails {
        return repository.getAppDetails(appId)
    }
}
