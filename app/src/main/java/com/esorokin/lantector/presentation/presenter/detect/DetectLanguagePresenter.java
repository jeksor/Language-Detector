package com.esorokin.lantector.presentation.presenter.detect;

import com.arellomobile.mvp.InjectViewState;
import com.esorokin.lantector.di.DependencyManager;
import com.esorokin.lantector.model.data.DetectedLanguageText;
import com.esorokin.lantector.model.interactor.detect.DetectLanguageInteractor;
import com.esorokin.lantector.model.network.exception.NoInternetException;
import com.esorokin.lantector.presentation.error.ErrorProcessor;
import com.esorokin.lantector.presentation.presenter.BasePresenter;
import com.esorokin.lantector.presentation.presenter.EventConsumerTransformer;
import com.esorokin.lantector.presentation.view.detect.DetectLanguageView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class DetectLanguagePresenter extends BasePresenter<DetectLanguageView> {
	@Inject
	DetectLanguageInteractor interactor;

	@Inject
	ErrorProcessor errorProcessor;

	public DetectLanguagePresenter() {
		DependencyManager.getAppComponent().inject(this);

		autoDispose(interactor.detectingEmitter()
				.observeOn(AndroidSchedulers.mainThread())
				.compose(EventConsumerTransformer.<DetectedLanguageText>builder()
						.showLoading(() -> getViewState().showDetectingProgress())
						.hideLoading(() -> getViewState().hideDetectingProgress())
						.showError(this::handleDetectingError)
						.hideError(() -> getViewState().hideError())
						.receiveData(detectedLanguageText -> getViewState().showDetectingResult(detectedLanguageText.getLanguage()))
						.build())
				.subscribe());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		interactor.onDestroy();
	}

	private void handleDetectingError(Throwable throwable) {
		if (throwable instanceof NoInternetException) {
			getViewState().noInternetConnection();
		} else {
			getViewState().showError(errorProcessor.processError(throwable));
		}
	}

	public void userClickStartDetect(String textToDetect) {
		interactor.requestDetectLanguage(textToDetect);
	}

	public void userClickRetryDetect(String textToDetect) {
		interactor.requestDetectLanguage(textToDetect);
	}

	public void userHideDetectingResult() {
		getViewState().hideDetectingResult();
	}

	public void userHideError() {
		getViewState().hideError();
	}
}
