package org.quaerense.spacewanderers.domain.usecase

import org.quaerense.spacewanderers.domain.AsteroidInfoRepository
import org.quaerense.spacewanderers.domain.entity.Asteroid

class GetAsteroidListUseCase(private val repository: AsteroidInfoRepository) {

    suspend operator fun invoke(page: Int = 1): List<Asteroid> {
        return repository.getAsteroidInfoList(LIMIT, LIMIT * page)
    }

    companion object {

        const val LIMIT = 20
    }
}