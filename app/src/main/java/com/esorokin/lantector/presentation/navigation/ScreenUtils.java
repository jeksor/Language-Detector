package com.esorokin.lantector.presentation.navigation;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.art.alligator.Screen;
import com.art.alligator.ScreenResolver;
import com.esorokin.lantector.di.DependencyManager;

public final class ScreenUtils {
	private ScreenUtils() {
		//utils
	}

	public static <T extends Screen> T getScreen(Activity activity, Class<T> screen) {
		return getAppScreenResolver().getScreen(activity, screen);
	}

	public static <T extends Screen> T getScreen(Fragment fragment, Class<T> screen) {
		return getAppScreenResolver().getScreen(fragment, screen);
	}

	private static ScreenResolver getAppScreenResolver() {
		return DependencyManager.getAppComponent().screenResolver();
	}
}
