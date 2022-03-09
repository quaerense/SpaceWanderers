package org.quaerense.spacewanderers.domain

import androidx.lifecycle.LiveData
import org.quaerense.spacewanderers.domain.entity.Asteroid

interface AsteroidInfoRepository {

    fun getAsteroidInfoList(): LiveData<List<Asteroid>>

    fun getAsteroidInfo(id: Int): LiveData<Asteroid>

    fun loadData()
}