package com.esorokin.lantector.app

import android.app.Application
import com.esorokin.lantector.di.DependencyManager
import com.esorokin.lantector.utils.BuildUtils
import timber.log.Timber

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupLogSystem()
        DependencyManager.init(this)
    }

    private fun setupLogSystem() {
        if (BuildUtils.isTurnLogs) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
