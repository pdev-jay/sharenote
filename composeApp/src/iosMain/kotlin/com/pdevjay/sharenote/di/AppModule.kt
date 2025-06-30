package com.pdevjay.sharenote.di

import com.pdevjay.sharenote.database.DatabaseDriverFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

val iosAppModule = module {
    single { DatabaseDriverFactory() } // iOS DriverFactory 제공
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