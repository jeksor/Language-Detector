package com.esorokin.lantector.model.interactor;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseInteractor {
	private final CompositeDisposable compositeDisposable;

	protected BaseInteractor() {
		compositeDisposable = new CompositeDisposable();
	}

	protected void autoDispose(Disposable disposable) {
		compositeDisposable.add(disposable);
	}

	public void onDestroy() {
		compositeDisposable.dispose();
	}
}
