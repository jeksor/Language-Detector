package com.esorokin.lantector.presentation.navigation

import com.art.alligator.navigationfactories.RegistryNavigationFactory
import com.esorokin.lantector.ui.activity.MainActivity
import com.esorokin.lantector.ui.activity.SplashActivity
import com.esorokin.lantector.ui.fragment.detect.DetectLanguageFragment
import com.esorokin.lantector.ui.fragment.history.HistoryFragment
import com.esorokin.lantector.utils.ext.registerActivity
import com.esorokin.lantector.utils.ext.registerFragment

class AppNavigationFactory : RegistryNavigationFactory() {
    init {
        registerActivity<AppScreen.Splash, SplashActivity>()
        registerActivity<AppScreen.Main, MainActivity>()
        registerFragment<AppScreen.DetectLanguage, DetectLanguageFragment>()
        registerFragment<AppScreen.History, HistoryFragment>()
    }
}
