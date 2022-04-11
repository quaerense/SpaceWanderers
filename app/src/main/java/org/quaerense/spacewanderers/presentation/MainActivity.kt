package org.quaerense.spacewanderers.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import kotlinx.coroutines.launch
import org.quaerense.spacewanderers.R
import org.quaerense.spacewanderers.data.database.AppDatabase
import org.quaerense.spacewanderers.data.repository.AsteroidInfoRepositoryImpl
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeSSLContext()
        val db = AppDatabase.getInstance(this)
        val asteroidDao = db.asteroidDao()
        val closeApproachDataDao = db.closeApproachDataDao()

        lifecycleScope.launch {
            asteroidDao.get(2004954).observe(this@MainActivity) {
                Log.d("MainActivity", it.toString())
            }
        }

        val repository = AsteroidInfoRepositoryImpl(application)
        repository.loadData()
    }

    private fun initializeSSLContext() {
        try {
            SSLContext.getInstance("TLSv1.2")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        try {
            ProviderInstaller.installIfNeeded(applicationContext)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
    }
}