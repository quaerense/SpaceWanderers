package org.quaerense.spacewanderers.domain

import androidx.lifecycle.LiveData
import org.quaerense.spacewanderers.domain.entity.Asteroid
import org.quaerense.spacewanderers.domain.state.DownloadState
import org.quaerense.spacewanderers.domain.usecase.GetAsteroidListUseCase.Companion.LIMIT

interface AsteroidInfoRepository {

    suspend fun getAsteroidInfoList(limit: Int = LIMIT, offset: Int): List<Asteroid>

    suspend fun getAsteroidInfo(id: Int): Asteroid

    fun loadData()

    fun stopDownload()

    fun getLastDownloadedPercent(): Int

    fun getDownloadState(): LiveData<DownloadState>
}