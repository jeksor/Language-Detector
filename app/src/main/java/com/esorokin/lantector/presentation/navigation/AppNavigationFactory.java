package com.esorokin.lantector.presentation.navigation;

import com.art.alligator.navigationfactories.RegistryNavigationFactory;
import com.esorokin.lantector.ui.activity.MainActivity;
import com.esorokin.lantector.ui.activity.SplashActivity;
import com.esorokin.lantector.ui.fragment.detect.DetectLanguageFragment;
import com.esorokin.lantector.ui.fragment.history.HistoryFragment;

public class AppNavigationFactory extends RegistryNavigationFactory {
	public AppNavigationFactory() {
		super();
		registerActivity(AppScreen.Splash.class, SplashActivity.class);
		registerActivity(AppScreen.Main.class, MainActivity.class);
		registerFragment(AppScreen.DetectLanguage.class, DetectLanguageFragment.class);
		registerFragment(AppScreen.History.class, HistoryFragment.class);
	}
}
