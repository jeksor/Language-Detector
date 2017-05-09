package com.esorokin.lantector.utils.ext

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

fun Disposable.autoDispose(container: AutoDisposableContainer) = container.autoDispose(this)

fun Single<*>.subscribeIgnoreResult() {
    this.toCompletable().onErrorComplete().subscribe()
}

fun Maybe<*>.subscribeIgnoreResult() {
    this.onErrorComplete().subscribe()
}

fun Completable.subscribeIgnoreResult() {
    this.onErrorComplete().subscribe()
}

fun Completable.uiThread(): Completable = observeOn(AndroidSchedulers.mainThread())
fun <Data> Maybe<Data>.uiThread(): Maybe<Data> = observeOn(AndroidSchedulers.mainThread())
fun <Data> Single<Data>.uiThread(): Single<Data> = observeOn(AndroidSchedulers.mainThread())
fun <Data> Observable<Data>.uiThread(): Observable<Data> = observeOn(AndroidSchedulers.mainThread())
fun <Data> Flowable<Data>.uiThread(): Flowable<Data> = observeOn(AndroidSchedulers.mainThread())