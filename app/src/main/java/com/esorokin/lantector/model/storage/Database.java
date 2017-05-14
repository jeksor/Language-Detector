package com.esorokin.lantector.model.storage;

import java.util.List;

import com.esorokin.lantector.model.data.DetectedLanguageText;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface Database {
	Completable addDetectedResult(DetectedLanguageText item);

	Completable deleteDetectedResult(DetectedLanguageText item);

	Single<List<DetectedLanguageText>> getAllDetectedResults();

	Completable removeAllDetectedResults();
}
