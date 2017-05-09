package com.esorokin.lantector.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.esorokin.lantector.di.DependencyManager
import com.esorokin.lantector.presentation.navigation.AppScreen
import com.esorokin.lantector.presentation.view.SplashView

@InjectViewState
class SplashPresenter : BasePresenter<SplashView>() {
    init {
        DependencyManager.appComponent.inject(this)
        navigator.replace(AppScreen.Main())
    }
}
