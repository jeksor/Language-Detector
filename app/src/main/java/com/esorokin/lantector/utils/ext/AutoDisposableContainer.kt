package com.esorokin.lantector.utils.ext

import io.reactivex.disposables.Disposable

interface AutoDisposableContainer {
    fun autoDispose(disposable: Disposable)
}