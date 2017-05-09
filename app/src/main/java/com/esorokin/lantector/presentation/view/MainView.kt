package com.esorokin.lantector.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.esorokin.lantector.presentation.navigation.AppScreen

@StateStrategyType(OneExecutionStateStrategy::class)
interface MainView : BaseView {
    fun openDrawer()
    fun closeDrawer()

    companion object TabScreens {
        val DETECT_LANGUAGE_SCREEN_NAME: String = AppScreen.DetectLanguage::class.java.simpleName
        val HISTORY_SCREEN_NAME: String = AppScreen.History::class.java.simpleName
    }
}