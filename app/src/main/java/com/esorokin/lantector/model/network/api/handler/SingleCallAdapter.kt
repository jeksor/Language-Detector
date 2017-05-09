package com.esorokin.lantector.model.network.api.handler

import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class SingleCallAdapter<BaseResponse>(
        private val networkScheduler: Scheduler,
        private val responseType: Type,
        private val networkErrorAdapter: NetworkErrorAdapter,
        private val httpResponseAdapter: HttpResponseAdapter<BaseResponse>) : CallAdapter<BaseResponse, Single<BaseResponse>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<BaseResponse>): Single<BaseResponse> {
        return CallExecuteObservable(call).singleOrError()
                .subscribeOn(networkScheduler)
                .onErrorResumeNext { throwable -> Single.error(networkErrorAdapter.adaptNetworkError(throwable)) }
                .map(httpResponseAdapter::adaptHttpResponse)
    }
}
