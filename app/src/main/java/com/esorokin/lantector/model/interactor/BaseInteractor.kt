package com.esorokin.lantector.model.interactor

import com.esorokin.lantector.utils.ext.AutoDisposableContainer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseInteractor protected constructor() : Disposable, AutoDisposableContainer {
    private var disposed = false
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onDestroy() {
        //override
    }

    override fun autoDispose(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun isDisposed(): Boolean = disposed

    final override fun dispose() {
        disposed = true
        compositeDisposable.clear()
        onDestroy()
    }
}
