package com.esorokin.lantector.ui.plugin

import android.support.v7.app.AppCompatActivity

import com.art.alligator.NavigationContext
import com.art.alligator.NavigationContextBinder
import com.art.alligator.Navigator
import com.art.alligator.TransitionAnimationProvider
import com.esorokin.lantector.di.DependencyManager
import com.esorokin.lantector.presentation.navigation.animation.DefaultAnimationProvider
import com.esorokin.lantector.ui.plugin.base.BasePlugin

import javax.inject.Inject

class NavigationPlugin(private val activity: AppCompatActivity,
                       private val transitionAnimationProvider: TransitionAnimationProvider = DefaultAnimationProvider()) : BasePlugin() {
    @Inject
    internal lateinit var navigationContextBinder: NavigationContextBinder

    @Inject
    internal lateinit var navigator: Navigator

    init {
        DependencyManager.appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        navigationContextBinder.bind(NavigationContext.Builder(activity)
                .transitionAnimationProvider(transitionAnimationProvider)
                .build())
    }

    override fun onPause() {
        super.onPause()
        navigationContextBinder.unbind()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigator.goBack()
    }
}
