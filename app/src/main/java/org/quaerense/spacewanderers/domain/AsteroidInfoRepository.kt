package org.quaerense.spacewanderers.domain

import androidx.lifecycle.LiveData
import org.quaerense.spacewanderers.domain.entity.Asteroid
import org.quaerense.spacewanderers.domain.state.DownloadState

interface AsteroidInfoRepository {

    fun getAsteroidInfoList(): LiveData<List<Asteroid>>

    fun getAsteroidInfo(id: Int): LiveData<Asteroid>

    fun getDownloadState(): LiveData<DownloadState>

    fun loadData()

    fun stopDownload()

    fun getLastDownloadedPercent(): Int
}