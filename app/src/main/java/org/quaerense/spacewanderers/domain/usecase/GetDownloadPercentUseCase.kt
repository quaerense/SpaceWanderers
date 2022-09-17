package org.quaerense.spacewanderers.domain.usecase

import org.quaerense.spacewanderers.domain.AsteroidInfoRepository

class GetDownloadPercentUseCase(private val repository: AsteroidInfoRepository) {

    operator fun invoke() = repository.getLastDownloadedPercent()
}