package com.esorokin.lantector.utils

import com.esorokin.lantector.BuildConfig

object BuildUtils {
    val isRelease: Boolean
        get() = !BuildConfig.DEBUG

    val isDebug: Boolean
        get() = BuildConfig.DEBUG

    val isTurnLogs: Boolean
        get() = BuildConfig.IS_TURN_LOGS
}
