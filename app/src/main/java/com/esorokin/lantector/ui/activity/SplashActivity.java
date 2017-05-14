package com.esorokin.lantector.ui.activity;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.esorokin.lantector.presentation.presenter.SplashPresenter;
import com.esorokin.lantector.presentation.view.SplashView;
import com.esorokin.lantector.ui.plugins.NavigationPlugin;

public class SplashActivity extends BaseActivity implements SplashView {
	@InjectPresenter
	SplashPresenter presenter;

	@Override
	protected void initPlugins() {
		super.initPlugins();
		compositionPlugin().attach(new NavigationPlugin(this));
	}
}
