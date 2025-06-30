package com.pdevjay.sharenote.util

expect class AppPreferences {
    fun isFirstLaunch(): Boolean
    fun setFirstLaunchDone()
}