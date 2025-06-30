package com.pdevjay.sharenote.util

import android.content.Context

actual class AppPreferences(private val context: Context) {

    private val prefs = context.getSharedPreferences("sharenote_prefs", Context.MODE_PRIVATE)

    actual fun isFirstLaunch(): Boolean {
        return prefs.getBoolean("is_first_launch", true)
    }

    actual fun setFirstLaunchDone() {
        prefs.edit().putBoolean("is_first_launch", false).apply()
    }
}