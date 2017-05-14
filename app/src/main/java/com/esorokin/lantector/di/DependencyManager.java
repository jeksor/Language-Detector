package com.esorokin.lantector.di;

import android.content.Context;

import com.esorokin.lantector.di.module.AndroidApplicationModule;
import com.esorokin.lantector.di.module.ApiModule;
import com.esorokin.lantector.di.module.NetworkModule;

public final class DependencyManager {
	//region Singleton
	private static volatile DependencyManager instance;

	private static DependencyManager get() {
		if (instance == null) {
			synchronized (DependencyManager.class) {
				if (instance == null) {
					instance = new DependencyManager();
				}
			}
		}

		return instance;
	}

	private DependencyManager() {/**/}
	//endregion

	private AppComponent appComponent;

	public void initAppComponent(Context context) {
		appComponent = DaggerAppComponent.builder()
				.androidApplicationModule(new AndroidApplicationModule(context))
				.networkModule(new NetworkModule())
				.apiModule(new ApiModule(context::getString))
				.build();
	}

	public static void init(Context context) {
		get().initAppComponent(context);
	}

	public static AppComponent getAppComponent() {
		return get().appComponent;
	}
}
