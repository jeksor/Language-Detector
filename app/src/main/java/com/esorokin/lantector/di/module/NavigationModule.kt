package com.esorokin.lantector.di.module

import com.art.alligator.*
import com.esorokin.lantector.presentation.navigation.AppNavigationFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    private val navigationFactory: AppNavigationFactory = AppNavigationFactory()
    private val navigator: AndroidNavigator = AndroidNavigator(navigationFactory)

    @Provides
    @Singleton
    fun provideNavigationFactory(): NavigationFactory = navigationFactory

    @Provides
    @Singleton
    fun provideNavigator(): Navigator = navigator

    @Provides
    @Singleton
    fun provideNavigationContextBinder(): NavigationContextBinder = navigator

    @Provides
    @Singleton
    fun provideScreenResolver(): ScreenResolver = navigator.screenResolver
}
