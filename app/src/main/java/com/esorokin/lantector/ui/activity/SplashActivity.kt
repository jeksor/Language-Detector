package com.esorokin.lantector.ui.activity

import com.arellomobile.mvp.presenter.InjectPresenter
import com.esorokin.lantector.presentation.presenter.SplashPresenter
import com.esorokin.lantector.presentation.view.SplashView

class SplashActivity : BaseActivity(), SplashView {
    @InjectPresenter
    internal lateinit var presenter: SplashPresenter

    override fun initPlugins() {
        super.initPlugins()
        compositionPlugin.attach(com.esorokin.lantector.ui.plugin.NavigationPlugin(this))
    }
}
