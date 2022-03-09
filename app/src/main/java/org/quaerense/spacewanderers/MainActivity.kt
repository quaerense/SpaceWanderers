package org.quaerense.spacewanderers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import org.quaerense.spacewanderers.data.repository.AsteroidInfoRepositoryImpl
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeSSLContext()

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