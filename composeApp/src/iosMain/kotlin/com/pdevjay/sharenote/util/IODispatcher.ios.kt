package com.pdevjay.sharenote.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val IODispatcher: CoroutineDispatcher
    get() = Dispatchers.Default