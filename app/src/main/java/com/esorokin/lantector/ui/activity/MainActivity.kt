package com.esorokin.lantector.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.arellomobile.mvp.presenter.InjectPresenter
import com.esorokin.lantector.R
import com.esorokin.lantector.presentation.navigation.AppScreen
import com.esorokin.lantector.presentation.navigation.TabsInfo
import com.esorokin.lantector.presentation.presenter.MainPresenter
import com.esorokin.lantector.presentation.view.MainView
import com.esorokin.lantector.presentation.view.MainView.TabScreens.DETECT_LANGUAGE_SCREEN_NAME
import com.esorokin.lantector.presentation.view.MainView.TabScreens.HISTORY_SCREEN_NAME
import com.esorokin.lantector.ui.plugin.SwitchFragmentNavigationPlugin
import com.esorokin.lantector.ui.plugin.ToolbarPlugin
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_default_simple_toolbar.*

class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {
    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    private lateinit var toolbarPlugin: ToolbarPlugin
    private lateinit var fragmentNavigationPlugin: SwitchFragmentNavigationPlugin

    private val tabsInfo: TabsInfo = TabsInfo().apply {
        add(DETECT_LANGUAGE_SCREEN_NAME, R.id.detectLanguageTab, AppScreen.DetectLanguage())
        add(HISTORY_SCREEN_NAME, R.id.historyTab, AppScreen.History())
    }

    override fun initPlugins() {
        super.initPlugins()
        toolbarPlugin = compositionPlugin.attach(ToolbarPlugin({ this }))
        fragmentNavigationPlugin = compositionPlugin.attach(SwitchFragmentNavigationPlugin(this, R.id.container, tabsInfo, onScreenSwitched()))
    }

    private fun onScreenSwitched(): (String) -> Unit = {
        navigationView.menu.findItem(tabsInfo.getTabId(it)).isChecked = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close).also {
            drawerLayout.addDrawerListener(it)
            it.syncState()
        }

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        presenter.userSelectTab(tabsInfo.getScreenName(item.itemId))
        return true
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }
}
