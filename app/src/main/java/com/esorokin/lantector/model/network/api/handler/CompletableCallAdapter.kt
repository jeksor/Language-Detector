package com.esorokin.lantector.model.network.api.handler

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class CompletableCallAdapter(
        private val networkScheduler: Scheduler,
        private val networkErrorAdapter: NetworkErrorAdapter,
        private val httpResponseAdapter: HttpResponseAdapter<Any>) : CallAdapter<Any, Completable> {

    override fun responseType(): Type {
        return Any::class.java
    }

    override fun adapt(call: Call<Any>): Completable {
        return CallExecuteObservable(call)
                .subscribeOn(networkScheduler)
                .onErrorResumeNext { throwable: Throwable -> Observable.error(networkErrorAdapter.adaptNetworkError(throwable)) }
                .map { httpResponseAdapter::adaptHttpResponse }
                .ignoreElements()
    }
}
