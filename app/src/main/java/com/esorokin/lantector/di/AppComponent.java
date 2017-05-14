package com.esorokin.lantector.di;

import com.art.alligator.ScreenResolver;
import com.esorokin.lantector.di.module.AndroidApplicationModule;
import com.esorokin.lantector.di.module.ApiModule;
import com.esorokin.lantector.di.module.BindingModule;
import com.esorokin.lantector.di.module.NavigationModule;
import com.esorokin.lantector.di.module.RequeryModule;
import com.esorokin.lantector.presentation.presenter.MainPresenter;
import com.esorokin.lantector.presentation.presenter.SplashPresenter;
import com.esorokin.lantector.presentation.presenter.detect.DetectLanguagePresenter;
import com.esorokin.lantector.presentation.presenter.history.HistoryPresenter;
import com.esorokin.lantector.ui.plugins.NavigationPlugin;
import com.esorokin.lantector.ui.plugins.SwitchFragmentNavigationPlugin;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
		AndroidApplicationModule.class,
		ApiModule.class,
		BindingModule.class,
		RequeryModule.class,
		NavigationModule.class})
public interface AppComponent {
	ScreenResolver screenResolver();

	void inject(NavigationPlugin navigationPlugin);

	void inject(MainPresenter mainPresenter);

	void inject(SplashPresenter splashPresenter);

	void inject(DetectLanguagePresenter detectLanguagePresenter);

	void inject(HistoryPresenter historyPresenter);

	void inject(SwitchFragmentNavigationPlugin switchFragmentNavigationPlugin);
}
