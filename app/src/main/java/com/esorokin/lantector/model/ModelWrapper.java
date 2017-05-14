package com.esorokin.lantector.model;

import android.support.annotation.NonNull;

import io.reactivex.SingleTransformer;
import io.reactivex.subjects.Subject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelWrapper<Data> {
	public static <Data> SingleTransformer<Data, Data> transitEventsToEmitter(Subject<ModelWrapper<Data>> emitter) {
		return source -> source
				.doOnSubscribe(disposable -> emitter.onNext(ModelWrapper.loading()))
				.doOnSuccess(credentials -> emitter.onNext(ModelWrapper.complete(credentials)))
				.doOnError(throwable -> emitter.onNext(ModelWrapper.error(throwable)));
	}

	public static <T> ModelWrapper<T> loading() {
		return new ModelWrapper<>(ModelState.LOADING, null, null);
	}

	public static <T> ModelWrapper<T> error(@NonNull Throwable throwable) {
		return new ModelWrapper<>(ModelState.ERROR, throwable, null);
	}

	public static <T> ModelWrapper<T> complete(@NonNull T data) {
		return new ModelWrapper<>(ModelState.COMPLETE, null, data);
	}

	public static <T> ModelWrapper<T> create(@NonNull ModelState state, @NonNull T data) {
		return new ModelWrapper<>(state, null, data);
	}

	public static <T> ModelWrapper<T> create(@NonNull ModelState state, @NonNull Throwable error) {
		return new ModelWrapper<>(state, error, null);
	}

	public static <T> ModelWrapper<T> create(@NonNull ModelState state, @NonNull T data, @NonNull Throwable error) {
		return new ModelWrapper<>(state, error, data);
	}

	private ModelState state;
	private Throwable error;
	private Data data;

	public boolean isLoading() {
		return ModelState.LOADING.equals(state);
	}

	public boolean isComplete() {
		return ModelState.COMPLETE.equals(state);
	}

	public boolean isError() {
		return ModelState.ERROR.equals(state);
	}

	private enum ModelState {
		LOADING,
		COMPLETE,
		ERROR
	}
}
