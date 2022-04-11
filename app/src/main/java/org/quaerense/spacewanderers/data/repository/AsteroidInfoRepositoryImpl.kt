package org.quaerense.spacewanderers.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import org.quaerense.spacewanderers.data.DownloadDataWorker
import org.quaerense.spacewanderers.data.database.AppDatabase
import org.quaerense.spacewanderers.data.database.mapper.AsteroidMapper
import org.quaerense.spacewanderers.data.database.mapper.CloseApproachDataMapper
import org.quaerense.spacewanderers.domain.AsteroidInfoRepository
import org.quaerense.spacewanderers.domain.entity.Asteroid

class AsteroidInfoRepositoryImpl(private val application: Application) : AsteroidInfoRepository {
    private val database = AppDatabase.getInstance(application)
    private val asteroidDao = database.asteroidDao()

    private val asteroidMapper = AsteroidMapper()
    private val closeApproachDataMapper = CloseApproachDataMapper()

    override fun getAsteroidInfoList(): LiveData<List<Asteroid>> {
        return Transformations.map(asteroidDao.getAll()) { asteroidWithCloseApproachDataList ->
            asteroidWithCloseApproachDataList.map { asteroidWithCloseApproachData ->
                asteroidMapper.mapDbModelToEntity(
                    asteroidWithCloseApproachData.asteroid,
                    closeApproachDataMapper.mapListDbModelToEntity(asteroidWithCloseApproachData.closeApproachData)
                )
            }
        }
    }

    override fun getAsteroidInfo(id: Int): LiveData<Asteroid> {
        return Transformations.map(asteroidDao.get(id)) {
            asteroidMapper.mapDbModelToEntity(
                it.asteroid,
                closeApproachDataMapper.mapListDbModelToEntity(it.closeApproachData)
            )
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            DownloadDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            DownloadDataWorker.makeRequest()
        )
    }
}