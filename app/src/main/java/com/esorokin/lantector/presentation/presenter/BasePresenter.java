package com.esorokin.lantector.presentation.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends MvpView> extends MvpPresenter<V> {
	private final CompositeDisposable compositeDisposable;

	public BasePresenter() {
		super();
		this.compositeDisposable = new CompositeDisposable();
	}

	protected void autoDispose(Disposable disposable) {
		compositeDisposable.add(disposable);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		compositeDisposable.dispose();
	}
}