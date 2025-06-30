package com.pdevjay.sharenote

import android.app.Application
import com.pdevjay.sharenote.di.androidAppModule
import com.pdevjay.sharenote.di.databaseModule
import com.pdevjay.sharenote.di.initKoin
import com.pdevjay.sharenote.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(androidAppModule, databaseModule, koinModule)
        }
    }
}