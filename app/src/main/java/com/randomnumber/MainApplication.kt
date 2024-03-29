package com.randomnumber

import android.app.Application
import com.randomnumber.di.provideAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(provideAllModules())
        }
    }
}