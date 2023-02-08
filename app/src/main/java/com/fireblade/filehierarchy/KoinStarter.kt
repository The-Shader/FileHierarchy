package com.fireblade.filehierarchy

import android.app.Application
import com.fireblade.filehierarchy.home.homeModule
import com.fireblade.network.networkModule
import com.fireblade.repository.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

object KoinStarter {
    @JvmStatic
    fun start(application: Application) {
        stopKoin()
        startKoin {
            androidContext(application)
            modules(
                listOf(
                    homeModule,
                    repositoryModule,
                    networkModule
                )
            )
        }
    }
}