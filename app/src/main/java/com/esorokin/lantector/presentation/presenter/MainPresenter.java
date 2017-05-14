package com.esorokin.lantector.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.art.alligator.Navigator;
import com.esorokin.lantector.di.DependencyManager;
import com.esorokin.lantector.presentation.view.MainView;

import javax.inject.Inject;

@InjectViewState
public class MainPresenter extends BasePresenter<MainView> {
	@Inject
	Navigator navigator;

	public MainPresenter() {
		DependencyManager.getAppComponent().inject(this);
		navigator.switchTo(MainView.DETECT_LANGUAGE_SCREEN_NAME);
	}

	public void userSelectTab(String screenName) {
		navigator.switchTo(screenName);
		getViewState().closeDrawer();
	}
}
