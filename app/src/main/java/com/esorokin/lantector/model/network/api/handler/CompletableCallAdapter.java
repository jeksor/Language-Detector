package com.esorokin.lantector.model.network.api.handler;

import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import retrofit2.Call;
import retrofit2.CallAdapter;

class CompletableCallAdapter implements CallAdapter<Object, Completable> {
	private final NetworkErrorAdapter networkErrorAdapter;
	private final HttpResponseAdapter<Object> httpResponseAdapter;
	private final Scheduler scheduler;

	public CompletableCallAdapter(@NonNull NetworkErrorAdapter networkErrorAdapter,
	                              @NonNull HttpResponseAdapter<Object> httpResponseAdapter,
	                              @NonNull Scheduler scheduler) {
		this.networkErrorAdapter = networkErrorAdapter;
		this.httpResponseAdapter = httpResponseAdapter;
		this.scheduler = scheduler;
	}

	@Override
	public Type responseType() {
		return Object.class;
	}

	@Override
	public Completable adapt(Call<Object> call) {
		return new CallExecuteObservable<>(call)
				.subscribeOn(scheduler)
				.onErrorResumeNext((Throwable throwable) -> Observable.error(networkErrorAdapter.adaptNetworkError(throwable)))
				.map(httpResponseAdapter::adaptHttpResponse)
				.ignoreElements();
	}
}
