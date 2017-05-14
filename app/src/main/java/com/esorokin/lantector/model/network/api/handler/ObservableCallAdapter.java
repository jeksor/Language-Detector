package com.esorokin.lantector.model.network.api.handler;

import java.lang.reflect.Type;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import retrofit2.Call;
import retrofit2.CallAdapter;

class ObservableCallAdapter<BaseResponse> implements CallAdapter<BaseResponse, Observable<BaseResponse>> {
	private final Type responseType;
	private final NetworkErrorAdapter networkErrorAdapter;
	private final HttpResponseAdapter<BaseResponse> httpResponseHandler;
	private final Scheduler scheduler;

	public ObservableCallAdapter(@NonNull Type responseType,
	                             @NonNull NetworkErrorAdapter networkErrorAdapter,
	                             @NonNull HttpResponseAdapter<BaseResponse> httpResponseAdapter,
	                             @NonNull Scheduler scheduler) {
		this.responseType = responseType;
		this.networkErrorAdapter = networkErrorAdapter;
		this.httpResponseHandler = httpResponseAdapter;
		this.scheduler = scheduler;
	}

	@Override
	public Type responseType() {
		return responseType;
	}

	@Override
	public Observable<BaseResponse> adapt(Call<BaseResponse> call) {
		return new CallExecuteObservable<>(call)
				.subscribeOn(scheduler)
				.onErrorResumeNext((Throwable throwable) -> Observable.error(networkErrorAdapter.adaptNetworkError(throwable)))
				.map(httpResponseHandler::adaptHttpResponse);
	}
}
