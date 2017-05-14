package com.esorokin.lantector.di.module;

import com.art.alligator.AndroidNavigator;
import com.art.alligator.NavigationContextBinder;
import com.art.alligator.NavigationFactory;
import com.art.alligator.Navigator;
import com.art.alligator.ScreenResolver;
import com.esorokin.lantector.presentation.navigation.AppNavigationFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {
	private final AppNavigationFactory navigationFactory = new AppNavigationFactory();
	private final AndroidNavigator navigator = new AndroidNavigator(navigationFactory);

	@Provides
	@Singleton
	NavigationFactory provideNavigationFactory() {
		return navigationFactory;
	}

	@Provides
	@Singleton
	Navigator provideNavigator() {
		return navigator;
	}

	@Provides
	@Singleton
	NavigationContextBinder provideNavigationContextBinder() {
		return navigator;
	}

	@Provides
	@Singleton
	ScreenResolver proScreenResolver() {
		return navigator.getScreenResolver();
	}
}
