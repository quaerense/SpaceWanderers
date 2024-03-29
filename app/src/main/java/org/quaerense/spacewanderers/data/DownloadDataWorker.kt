package org.quaerense.spacewanderers.data

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.work.*
import org.quaerense.spacewanderers.data.database.AppDatabase
import org.quaerense.spacewanderers.data.database.mapper.AsteroidMapper
import org.quaerense.spacewanderers.data.database.mapper.CloseApproachDataMapper
import org.quaerense.spacewanderers.data.network.ApiFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLProtocolException

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
    private val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)

    override suspend fun doWork(): Result {
        val startPage = preferenceManager.getInt(START_PAGE, UNDEFINED_PAGE)
        var currentPage = startPage
        val totalPages: Int
        try {
            totalPages =
                apiService.getAllAsteroids(page = UNDEFINED_PAGE).page?.totalPages ?: UNDEFINED_PAGE
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }

        while (currentPage < totalPages) {
            try {
                downloadData(currentPage)
                val percent = currentPage * 100 / totalPages
                setProgress(workDataOf(DOWNLOAD_PERCENT to percent))
                preferenceManager.edit().putInt(DOWNLOAD_PERCENT, percent).apply()
                currentPage++
            } catch (e: Exception) {
                e.printStackTrace()

                if (e is SSLProtocolException) {
                    return Result.retry()
                }

                return Result.failure()
            }
        }

        return Result.success()
    }

    private suspend fun downloadData(page: Int) {
        val nearEarthObjectDto = apiService.getAllAsteroids(page = page).asteroids ?: listOf()
        for (dto in nearEarthObjectDto) {
            val asteroidDbModel = asteroidMapper.mapNearEarthObjectDtoToAsteroidDbModel(dto)
            val closeApproachDataDbModel = closeApproachDataMapper.mapListDtoToListDbModel(
                asteroidDbModel.id,
                dto.closeApproachData
            )

            asteroidDao.insert(asteroidDbModel)
            closeApproachDataDao.insert(closeApproachDataDbModel)
        }

        preferenceManager.edit().putInt(START_PAGE, page).apply()
    }

    companion object {

        const val NAME = "DownloadDataWorker"
        const val DOWNLOAD_PERCENT = "DownloadPercent"
        private const val START_PAGE = "StartPage"
        private const val UNDEFINED_PAGE = 0

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<DownloadDataWorker>()
                .setConstraints(makeConstraints())
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .build()
        }

        private fun makeConstraints(): Constraints {
            return Constraints.Builder()
                .build()
        }
    }
}