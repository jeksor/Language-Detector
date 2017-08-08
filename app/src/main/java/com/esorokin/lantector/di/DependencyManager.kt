package com.esorokin.lantector.di

import android.content.Context
import com.esorokin.lantector.di.module.AndroidApplicationModule

object DependencyManager {
    lateinit var appComponent: AppComponent

    fun init(context: Context) {
        initAppComponent(context)
    }

    private fun initAppComponent(context: Context) {
        appComponent = DaggerAppComponent.builder()
                .androidApplicationModule(AndroidApplicationModule(context))
                .build()
    }
}
