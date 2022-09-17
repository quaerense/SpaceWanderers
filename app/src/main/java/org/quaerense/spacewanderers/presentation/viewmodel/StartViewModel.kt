package org.quaerense.spacewanderers.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.quaerense.spacewanderers.data.repository.AsteroidInfoRepositoryImpl
import org.quaerense.spacewanderers.domain.usecase.GetDownloadPercentUseCase
import org.quaerense.spacewanderers.domain.usecase.GetDownloadProgressUseCase
import org.quaerense.spacewanderers.domain.usecase.StartDownloadUseCase
import org.quaerense.spacewanderers.domain.usecase.StopDownloadUseCase

class StartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AsteroidInfoRepositoryImpl(application)
    private val startDownloadUseCase = StartDownloadUseCase(repository)
    private val stopDownloadUseCase = StopDownloadUseCase(repository)
    private val getDownloadProgressUseCase = GetDownloadProgressUseCase(repository)
    private val getDownloadPercentUseCase = GetDownloadPercentUseCase(repository)

    fun startDownload() = startDownloadUseCase()
    fun stopDownload() = stopDownloadUseCase()
    fun downloadProgress() = getDownloadProgressUseCase()
    fun getDownloadPercent() = getDownloadPercentUseCase()
}