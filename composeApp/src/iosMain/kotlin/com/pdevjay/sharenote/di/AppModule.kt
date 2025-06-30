package com.pdevjay.sharenote.di

import com.pdevjay.sharenote.database.DatabaseDriverFactory
import com.pdevjay.sharenote.util.AppPreferences
import org.koin.core.context.startKoin
import org.koin.dsl.module

val iosAppModule = module {
    single { DatabaseDriverFactory() } // iOS DriverFactory 제공
    single { AppPreferences() }
}

fun iosInitKoin(){
    startKoin {
        modules(
            iosAppModule,
            databaseModule,
            koinModule
        )
    }
}