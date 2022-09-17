package org.quaerense.spacewanderers.domain.usecase

import org.quaerense.spacewanderers.domain.AsteroidInfoRepository

class StartDownloadUseCase(private val repository: AsteroidInfoRepository) {

    operator fun invoke() = repository.loadData()
}