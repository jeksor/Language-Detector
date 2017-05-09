package com.esorokin.lantector.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.art.alligator.Navigator
import com.esorokin.lantector.utils.ext.AutoDisposableContainer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BasePresenter<V : MvpView> : MvpPresenter<V>(), AutoDisposableContainer {
    @Inject
    internal lateinit var navigator: Navigator

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun autoDispose(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}