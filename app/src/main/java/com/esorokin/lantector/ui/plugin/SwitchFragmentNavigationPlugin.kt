package com.esorokin.lantector.ui.plugin

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.art.alligator.NavigationContext
import com.art.alligator.NavigationContextBinder
import com.art.alligator.NavigationFactory
import com.art.alligator.ScreenSwitcher
import com.art.alligator.screenswitchers.FragmentScreenSwitcher
import com.esorokin.lantector.di.DependencyManager
import com.esorokin.lantector.presentation.navigation.TabsInfo
import com.esorokin.lantector.ui.plugin.base.BasePlugin
import javax.inject.Inject

class SwitchFragmentNavigationPlugin(
        private val activity: AppCompatActivity,
        private @IdRes val containerId: Int,
        private val tabsInfo: TabsInfo,
        private val onScreenSwitched: (String) -> Unit = {}
) : BasePlugin() {
    @Inject
    internal lateinit var navigationContextBinder: NavigationContextBinder

    @Inject
    internal lateinit var navigationFactory: NavigationFactory

    init {
        DependencyManager.appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        navigationContextBinder.bind(NavigationContext.Builder(activity)
                .screenSwitcher(createFragmentSwitcher())
                .build())
    }

    private fun createFragmentSwitcher(): ScreenSwitcher = object : FragmentScreenSwitcher(activity.supportFragmentManager, containerId) {
        override fun createFragment(screenName: String): Fragment = navigationFactory.createFragment(tabsInfo.getScreen(screenName))
        override fun onScreenSwitched(screenName: String) = onScreenSwitched.invoke(screenName)
    }

    override fun onPause() {
        super.onPause()
        navigationContextBinder.unbind()
    }
}
