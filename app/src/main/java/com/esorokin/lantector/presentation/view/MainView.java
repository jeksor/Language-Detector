package com.esorokin.lantector.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.esorokin.lantector.presentation.navigation.AppScreen;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface MainView extends BaseView {
	String DETECT_LANGUAGE_SCREEN_NAME = AppScreen.DetectLanguage.class.getSimpleName();
	String HISTORY_SCREEN_NAME = AppScreen.History.class.getSimpleName();

	void openDrawer();
	void closeDrawer();
}
