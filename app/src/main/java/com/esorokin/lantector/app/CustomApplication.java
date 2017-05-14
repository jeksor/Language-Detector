package com.esorokin.lantector.app;

import android.app.Application;

import com.esorokin.lantector.di.DependencyManager;
import com.esorokin.lantector.utils.BuildUtils;

import timber.log.Timber;

public class CustomApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		setupLogSystem();
		DependencyManager.init(this);
	}

	private void setupLogSystem() {
		if (BuildUtils.isTurnLogs()) {
			Timber.plant(new Timber.DebugTree());
		}
	}
}
