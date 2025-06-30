package com.pdevjay.sharenote.util

import platform.Foundation.NSUserDefaults

actual class AppPreferences {

    private val defaults = NSUserDefaults.standardUserDefaults

    actual fun isFirstLaunch(): Boolean {
        return defaults.boolForKey("is_first_launch").not()
    }

    actual fun setFirstLaunchDone() {
        defaults.setBool(true, forKey = "is_first_launch")
    }
}