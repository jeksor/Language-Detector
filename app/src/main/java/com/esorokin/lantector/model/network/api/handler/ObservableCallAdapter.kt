package com.esorokin.lantector.model.network.api.handler

import io.reactivex.Observable
import io.reactivex.Scheduler
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ObservableCallAdapter<BaseResponse>(
        private val networkScheduler: Scheduler,
        private val responseType: Type,
        private val networkErrorAdapter: NetworkErrorAdapter,
        private val httpResponseHandler: HttpResponseAdapter<BaseResponse>) : CallAdapter<BaseResponse, Observable<BaseResponse>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<BaseResponse>): Observable<BaseResponse> {
        return CallExecuteObservable(call)
                .subscribeOn(networkScheduler)
                .onErrorResumeNext { throwable: Throwable -> Observable.error(networkErrorAdapter.adaptNetworkError(throwable)) }
                .map(httpResponseHandler::adaptHttpResponse)
    }
}
