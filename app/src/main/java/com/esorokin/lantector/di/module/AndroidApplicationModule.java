package com.esorokin.lantector.di.module;

import android.content.Context;

import com.esorokin.lantector.app.StringProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidApplicationModule {
	private final Context context;

	public AndroidApplicationModule(Context context) {
		this.context = context;
	}

	@Provides
	@Singleton
	Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	StringProvider provideStringProvider() {
		return context::getString;
	}
}
