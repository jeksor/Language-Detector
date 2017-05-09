package com.esorokin.lantector.di

import com.art.alligator.Navigator
import com.art.alligator.ScreenResolver
import com.esorokin.lantector.di.module.*
import com.esorokin.lantector.presentation.presenter.MainPresenter
import com.esorokin.lantector.presentation.presenter.SplashPresenter
import com.esorokin.lantector.presentation.presenter.detect.DetectLanguagePresenter
import com.esorokin.lantector.presentation.presenter.history.HistoryPresenter
import com.esorokin.lantector.ui.plugin.NavigationPlugin
import com.esorokin.lantector.ui.plugin.SwitchFragmentNavigationPlugin
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidApplicationModule::class,
        RequeryModule::class,
        ApiModule::class,
        NavigationModule::class,
        BindingModule::class))
interface AppComponent {
    fun screenResolver(): ScreenResolver
    fun navigator(): Navigator

    fun inject(switchFragmentNavigationPlugin: SwitchFragmentNavigationPlugin)
    fun inject(navigationPlugin: NavigationPlugin)

    fun inject(splashPresenter: SplashPresenter)
    fun inject(detectLanguagePresenter: DetectLanguagePresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(historyPresenter: HistoryPresenter)
}
