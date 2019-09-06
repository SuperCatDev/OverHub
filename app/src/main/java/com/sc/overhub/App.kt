package com.sc.overhub

import android.app.Application
import com.sc.overhub.di.domainModule
import com.sc.overhub.di.repositoryModule
import com.sc.overhub.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    domainModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}