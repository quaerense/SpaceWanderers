package org.quaerense.spacewanderers.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.preference.PreferenceManager
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import org.quaerense.spacewanderers.data.DownloadDataWorker
import org.quaerense.spacewanderers.data.DownloadDataWorker.Companion.DOWNLOAD_PERCENT
import org.quaerense.spacewanderers.data.database.AppDatabase
import org.quaerense.spacewanderers.data.database.mapper.AsteroidMapper
import org.quaerense.spacewanderers.data.database.mapper.CloseApproachDataMapper
import org.quaerense.spacewanderers.domain.AsteroidInfoRepository
import org.quaerense.spacewanderers.domain.entity.Asteroid
import org.quaerense.spacewanderers.domain.state.*

class AsteroidInfoRepositoryImpl(private val application: Application) : AsteroidInfoRepository {

    private val preferenceManager = PreferenceManager.getDefaultSharedPreferences(application)
    private val database = AppDatabase.getInstance(application)
    private val asteroidDao = database.asteroidDao()

    private val asteroidMapper = AsteroidMapper()
    private val closeApproachDataMapper = CloseApproachDataMapper()


    override suspend fun getAsteroidInfoList(limit: Int, offset: Int): List<Asteroid> {
        val asteroidWithCloseApproachDataList = asteroidDao.getList(limit, offset)

        return asteroidWithCloseApproachDataList.map { asteroidWithCloseApproachData ->
            asteroidMapper.mapDbModelToEntity(
                asteroidWithCloseApproachData.asteroid,
                closeApproachDataMapper.mapListDbModelToEntity(asteroidWithCloseApproachData.closeApproachData)
            )
        }
    }

    override suspend fun getAsteroidInfo(id: Int): Asteroid {
        val asteroidWithCloseApproachData = asteroidDao.get(id)

        return asteroidMapper.mapDbModelToEntity(
            asteroidWithCloseApproachData.asteroid,
            closeApproachDataMapper.mapListDbModelToEntity(asteroidWithCloseApproachData.closeApproachData)
        )
    }

    override fun loadData() {
        WorkManager.getInstance(application).apply {
            enqueueUniqueWork(
                DownloadDataWorker.NAME,
                ExistingWorkPolicy.REPLACE,
                DownloadDataWorker.makeRequest()
            )
        }
    }

    override fun stopDownload() {
        WorkManager.getInstance(application).cancelAllWork()
    }

    override fun getLastDownloadedPercent(): Int {
        return preferenceManager.getInt(DOWNLOAD_PERCENT, 0)
    }

    override fun getDownloadState(): LiveData<DownloadState> {
        return Transformations.map(
            WorkManager
                .getInstance(application)
                .getWorkInfosForUniqueWorkLiveData(DownloadDataWorker.NAME)
        ) { workInfo ->
            var state: DownloadState = Pending

            if (!workInfo.isNullOrEmpty()) {
                val percent = workInfo[0].progress.getInt(
                    DOWNLOAD_PERCENT,
                    getLastDownloadedPercent()
                )

                state = when (workInfo[0].state) {
                    WorkInfo.State.RUNNING -> Loading(percent)
                    WorkInfo.State.FAILED -> Failed
                    WorkInfo.State.ENQUEUED -> Restarting
                    WorkInfo.State.SUCCEEDED -> Succeeded
                    else -> Pending
                }
            }

            state
        }
    }
}