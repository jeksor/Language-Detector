package com.esorokin.lantector.model;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public final class ModelUtils {
	private ModelUtils() {
		//utils
	}

	public static void subscribeIgnoreResult(Single<?> source) {
		source.toCompletable().onErrorComplete().subscribe();
	}

	public static void subscribeIgnoreResult(Maybe<?> source) {
		source.onErrorComplete().subscribe();
	}

	public static void subscribeIgnoreResult(Completable source) {
		source.onErrorComplete().subscribe();
	}
}
