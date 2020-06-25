package com.faraji.challenge.vira.app

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.faraji.challenge.vira.data.dataProviderModule
import com.faraji.challenge.vira.data.repositoryModule
import com.faraji.challenge.vira.presentation.viewModelModule
import org.koin.core.context.startKoin
import timber.log.Timber


class ChallengeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // your modules
            modules(
                listOf(
                    getApplicationModule(this@ChallengeApp),
                    dataProviderModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
        Timber.plant(Timber.DebugTree())
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }
}