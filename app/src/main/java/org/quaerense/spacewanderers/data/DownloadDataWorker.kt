package org.quaerense.spacewanderers.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import org.quaerense.spacewanderers.data.database.AppDatabase
import org.quaerense.spacewanderers.data.database.mapper.AsteroidMapper
import org.quaerense.spacewanderers.data.database.mapper.CloseApproachDataMapper
import org.quaerense.spacewanderers.data.network.ApiFactory

class DownloadDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    private val database = AppDatabase.getInstance(context)
    private val asteroidDao = database.asteroidDao()
    private val closeApproachDataDao = database.closeApproachDataDao()
    private val apiService = ApiFactory.apiService
    private val asteroidMapper = AsteroidMapper()
    private val closeApproachDataMapper = CloseApproachDataMapper()

    override suspend fun doWork(): Result {
        asteroidDao.deleteAll()
        closeApproachDataDao.deleteAll()

        val totalPages = apiService.getAllAsteroids(page = 0).page?.totalPages ?: 0
        for (page in 0..totalPages) {
            try {
                downloadData(page)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return Result.success()
    }

    private suspend fun downloadData(page: Int) {
        val nearEarthObjectDto =
            apiService.getAllAsteroids(page = page).nearEarthObjects ?: listOf()
        for (dto in nearEarthObjectDto) {
            val asteroidDbModel =
                asteroidMapper.mapNearEarthObjectDtoToAsteroidDbModel(dto)
            val closeApproachDataDbModel = closeApproachDataMapper.mapListDtoToListDbModel(
                asteroidDbModel.id,
                dto.closeApproachData
            )

            asteroidDao.insert(asteroidDbModel)
            closeApproachDataDao.insert(closeApproachDataDbModel)
        }
    }

    companion object {

        const val NAME = "DownloadDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<DownloadDataWorker>().build()
        }
    }
}