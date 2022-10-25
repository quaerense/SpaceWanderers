package org.quaerense.spacewanderers.domain.usecase

import org.quaerense.spacewanderers.domain.AsteroidInfoRepository

class GetDownloadStateUseCase(private val repository: AsteroidInfoRepository) {

    operator fun invoke() = repository.getDownloadState()
}