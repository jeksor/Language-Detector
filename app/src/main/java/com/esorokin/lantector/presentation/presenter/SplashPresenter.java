package com.esorokin.lantector.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.art.alligator.Navigator;
import com.esorokin.lantector.di.DependencyManager;
import com.esorokin.lantector.presentation.navigation.AppScreen;
import com.esorokin.lantector.presentation.view.SplashView;

import javax.inject.Inject;

@InjectViewState
public class SplashPresenter extends BasePresenter<SplashView> {
	@Inject
	Navigator navigator;

	public SplashPresenter() {
		DependencyManager.getAppComponent().inject(this);
		navigator.replace(new AppScreen.Main());
	}
}
