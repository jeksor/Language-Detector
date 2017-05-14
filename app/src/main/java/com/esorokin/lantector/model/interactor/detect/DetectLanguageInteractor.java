package com.esorokin.lantector.model.interactor.detect;

import java.util.List;

import com.esorokin.lantector.model.ModelUtils;
import com.esorokin.lantector.model.ModelWrapper;
import com.esorokin.lantector.model.data.DetectedLanguageText;
import com.esorokin.lantector.model.interactor.BaseInteractor;
import com.esorokin.lantector.model.service.DetectLanguageService;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class DetectLanguageInteractor extends BaseInteractor {
	@Inject
	DetectLanguageService detectLanguageService;

	@Inject
	public DetectLanguageInteractor() {
		//inject
	}

	private Subject<ModelWrapper<DetectedLanguageText>> detectingLanguageEmitter = PublishSubject.create();

	public Observable<ModelWrapper<List<DetectedLanguageText>>> historyEmitter() {
		return detectLanguageService.historyEmitter();
	}

	public Observable<ModelWrapper<DetectedLanguageText>> detectingEmitter() {
		return detectingLanguageEmitter;
	}

	public void requestHistory() {
		detectLanguageService.requestHistory();
	}

	public void requestDetectLanguage(String text) {
		ModelUtils.subscribeIgnoreResult(detectLanguageService.detectedLanguage(text)
				.compose(ModelWrapper.transitEventsToEmitter(detectingLanguageEmitter)));
	}
}
