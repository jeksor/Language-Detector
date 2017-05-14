package com.esorokin.lantector.di.module;

import android.content.Context;

import com.esorokin.lantector.model.data.Models;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.sql.EntityDataStore;

@Module
public class RequeryModule {
	public static final String DB_NAME = "lantector.db";
	public static final int DB_VERSION = 1;

	@Provides
	@Singleton
	DatabaseSource provideSource(Context context) {
		return new DatabaseSource(context, Models.DEFAULT, DB_NAME, DB_VERSION);
	}

	@Provides
	@Singleton
	EntityDataStore<Persistable> provideStore(DatabaseSource databaseSource) {
		return new EntityDataStore<>(databaseSource.getConfiguration());
	}
}
