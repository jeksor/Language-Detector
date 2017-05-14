package com.esorokin.lantector.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.esorokin.lantector.model.ModelUtils;
import com.esorokin.lantector.model.ModelWrapper;
import com.esorokin.lantector.model.data.DetectedLanguageText;
import com.esorokin.lantector.model.mapper.DetectLanguageTextMapper;
import com.esorokin.lantector.model.network.api.WatsonPlatformApi;
import com.esorokin.lantector.model.storage.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

@Singleton
public class DetectLanguageService {
	@Inject
	WatsonPlatformApi watsonPlatformApi;

	@Inject
	DetectLanguageTextMapper detectedTextMapper;

	@Inject
	Database database;

	private Subject<ModelWrapper<List<DetectedLanguageText>>> historyEmitter = PublishSubject.create();

	@Inject
	public DetectLanguageService() {
		//inject
	}

	public Observable<ModelWrapper<List<DetectedLanguageText>>> historyEmitter() {
		return historyEmitter;
	}

	public void requestHistory() {
		ModelUtils.subscribeIgnoreResult(getHistory());
	}

	public Single<List<DetectedLanguageText>> getHistory() {
		return database.getAllDetectedResults()
				.map(detectedLanguageTexts -> {
					detectedLanguageTexts = new ArrayList<>(detectedLanguageTexts);
					Collections.sort(detectedLanguageTexts, (t1, t2) -> t1.getDate().compareTo(t2.getDate()));
					return detectedLanguageTexts;
				})
				.compose(ModelWrapper.transitEventsToEmitter(historyEmitter));
	}

	public Single<DetectedLanguageText> detectedLanguage(String text) {
		return watsonPlatformApi.detectLanguage(text, WatsonPlatformApi.API_KEY)
				.map(detectLanguageResponse -> detectedTextMapper.convert(detectLanguageResponse, text))
				.doOnSuccess(detectedLanguageText -> {
					ModelUtils.subscribeIgnoreResult(database.addDetectedResult(detectedLanguageText));

					requestHistory();
				});
	}
}
