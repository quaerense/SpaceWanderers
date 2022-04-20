package org.quaerense.spacewanderers.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.quaerense.spacewanderers.R
import org.quaerense.spacewanderers.data.repository.AsteroidInfoRepositoryImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = AsteroidInfoRepositoryImpl(application)
        repository.loadData()
    }
}