package com.pdevjay.sharenote.di

import com.pdevjay.sharenote.database.DatabaseDriverFactory
import com.pdevjay.sharenote.util.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidAppModule = module {
    // Android Context를 사용하여 DriverFactory를 제공합니다.
    // androidContext()는 Koin이 Application Context를 주입해줍니다.
    single { DatabaseDriverFactory(androidContext()) }
    single { AppPreferences(androidContext()) }
}