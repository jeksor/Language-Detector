package com.esorokin.lantector.presentation.presenter;

import com.esorokin.lantector.model.ModelWrapper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import lombok.experimental.Builder;
import timber.log.Timber;

@Builder
public final class EventConsumerTransformer<Data> implements ObservableTransformer<ModelWrapper<Data>, ModelWrapper<Data>> {
	private Action showLoading;
	private Action hideLoading;
	private Consumer<Throwable> showError;
	private Action hideError;
	private Consumer<Data> receiveData;

	@Override
	public ObservableSource<ModelWrapper<Data>> apply(Observable<ModelWrapper<Data>> upstream) {
		return upstream.doOnNext(dataModelWrapper -> {
			if (dataModelWrapper.isLoading()) {
				hideError();
				showLoading();
			}

			if (dataModelWrapper.isError()) {
				hideLoading();
				showError(dataModelWrapper.getError());
			}

			if (dataModelWrapper.isComplete()) {
				hideError();
				hideLoading();
				receiveData(dataModelWrapper.getData());
			}
		});
	}

	private void receiveData(Data data) throws Exception {
		if (receiveData != null) {
			receiveData.accept(data);
		} else {
			Timber.e("EventConsumer receive ModelWrapper.isComplete == true, but receiveData consumer not set.");
		}
	}

	private void showError(Throwable error) throws Exception {
		if (showError != null) {
			showError.accept(error);
		} else {
			Timber.e("EventConsumer receive ModelWrapper.isError == true, but showError consumer not set.");
		}
	}

	private void hideError() throws Exception {
		if (hideError != null) {
			hideError.run();
		} else {
			Timber.e("EventConsumer try hide error, but hideError action not set.");
		}
	}

	private void showLoading() throws Exception {
		if (showLoading != null) {
			showLoading.run();
		} else {
			Timber.e("EventConsumer receive ModelWrapper.isLoading == true, but showLoading action not set.");
		}
	}

	private void hideLoading() throws Exception {
		if (hideLoading != null) {
			hideLoading.run();
		} else {
			Timber.e("EventConsumer try hide loading, but hideLoading action not set.");
		}
	}
}
