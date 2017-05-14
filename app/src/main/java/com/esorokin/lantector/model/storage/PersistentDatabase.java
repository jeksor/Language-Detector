package com.esorokin.lantector.model.storage;

import java.util.List;

import com.esorokin.lantector.model.data.DetectedLanguageText;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.requery.Persistable;
import io.requery.sql.EntityDataStore;

@Singleton
public class PersistentDatabase implements Database {
	@Inject
	EntityDataStore<Persistable> store;

	@Inject
	public PersistentDatabase() {
		//inject
	}

	@Override
	public Completable addDetectedResult(DetectedLanguageText item) {
		return Completable.fromAction(() -> store.insert(item));
	}

	@Override
	public Completable deleteDetectedResult(DetectedLanguageText item) {
		return Completable.fromAction(() -> store.delete(item));
	}

	@Override
	public Single<List<DetectedLanguageText>> getAllDetectedResults() {
		return Single.just(store.select(DetectedLanguageText.class).get().toList());
	}

	@Override
	public Completable removeAllDetectedResults() {
		return Completable.fromAction(() -> store.delete(DetectedLanguageText.class));
	}
}
