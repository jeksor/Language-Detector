package com.esorokin.lantector.utils.ext

import com.esorokin.lantector.model.ModelWrapper
import io.reactivex.Single
import io.reactivex.subjects.Subject

fun <Data> Single<Data>.transitEventsToEmitter(emitter: Subject<ModelWrapper<Data>>): Single<Data> {
    return this.doOnSubscribe { emitter.onNext(ModelWrapper.loading()) }
            .doOnSuccess { emitter.onNext(ModelWrapper.Companion.complete(it)) }
            .doOnError { emitter.onNext(ModelWrapper.Companion.error(it)) }
}

fun <Data> Single<Data>.transitSuccessToEmitter(emitter: Subject<ModelWrapper<Data>>): Single<Data> {
    return doOnSuccess { emitter.onNext(ModelWrapper.Companion.complete(it)) }
}
