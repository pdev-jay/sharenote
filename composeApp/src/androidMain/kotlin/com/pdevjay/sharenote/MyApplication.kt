package com.pdevjay.sharenote

import android.app.Application
import com.pdevjay.sharenote.di.initKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}