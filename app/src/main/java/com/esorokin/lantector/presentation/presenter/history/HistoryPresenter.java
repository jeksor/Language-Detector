package com.esorokin.lantector.presentation.presenter.history;

import java.util.List;

import com.arellomobile.mvp.InjectViewState;
import com.esorokin.lantector.di.DependencyManager;
import com.esorokin.lantector.model.data.DetectedLanguageText;
import com.esorokin.lantector.model.interactor.detect.DetectLanguageInteractor;
import com.esorokin.lantector.presentation.error.ErrorProcessor;
import com.esorokin.lantector.presentation.presenter.BasePresenter;
import com.esorokin.lantector.presentation.presenter.EventConsumerTransformer;
import com.esorokin.lantector.presentation.view.history.HistoryView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class HistoryPresenter extends BasePresenter<HistoryView> {
	@Inject
	DetectLanguageInteractor interactor;

	@Inject
	ErrorProcessor errorProcessor;

	public HistoryPresenter() {
		DependencyManager.getAppComponent().inject(this);

		autoDispose(interactor.historyEmitter()
				.observeOn(AndroidSchedulers.mainThread())
				.compose(EventConsumerTransformer.<List<DetectedLanguageText>>builder()
						.showError(throwable -> getViewState().showError(errorProcessor.processError(throwable)))
						.hideError(() -> getViewState().hideError())
						.receiveData(this::handleItems)
						.build())
				.subscribe());

		interactor.requestHistory();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		interactor.onDestroy();
	}

	private void handleItems(List<DetectedLanguageText> items) {
		if (items.isEmpty()) {
			getViewState().hideItems();
		} else {
			getViewState().showItems(items);
		}
	}

	public void userHideError() {
		getViewState().hideError();
	}
}
