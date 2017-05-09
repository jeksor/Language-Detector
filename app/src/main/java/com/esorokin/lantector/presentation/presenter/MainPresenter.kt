package com.esorokin.lantector.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.esorokin.lantector.di.DependencyManager
import com.esorokin.lantector.presentation.view.MainView

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {
    init {
        DependencyManager.appComponent.inject(this)
        navigator.switchTo(MainView.TabScreens.DETECT_LANGUAGE_SCREEN_NAME)
    }

    fun userSelectTab(screenName: String) {
        navigator.switchTo(screenName)
        viewState.closeDrawer()
    }
}