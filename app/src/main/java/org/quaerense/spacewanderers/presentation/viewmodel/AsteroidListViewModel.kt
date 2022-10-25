package org.quaerense.spacewanderers.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.quaerense.spacewanderers.data.repository.AsteroidInfoRepositoryImpl
import org.quaerense.spacewanderers.domain.entity.Asteroid
import org.quaerense.spacewanderers.domain.usecase.GetAsteroidListUseCase

class AsteroidListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AsteroidInfoRepositoryImpl(application)
    private val getAsteroidListUseCase = GetAsteroidListUseCase(repository)

    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidList

    fun getAsteroidList(page: Int) {
        viewModelScope.launch {
            _asteroidList.value = getAsteroidListUseCase(page) ?: emptyList()
        }
    }
}