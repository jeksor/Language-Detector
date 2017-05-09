package com.esorokin.lantector.model.network.api.handler

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class FlowableCallAdapter<BaseResponse>(
        private val networkScheduler: Scheduler,
        private val responseType: Type,
        private val networkErrorAdapter: NetworkErrorAdapter,
        private val httpResponseHandler: HttpResponseAdapter<BaseResponse>) : CallAdapter<BaseResponse, Flowable<BaseResponse>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<BaseResponse>): Flowable<BaseResponse> {
        return CallExecuteObservable(call).toFlowable(BackpressureStrategy.LATEST)
                .subscribeOn(networkScheduler)
                .onErrorResumeNext { throwable: Throwable -> Flowable.error(networkErrorAdapter.adaptNetworkError(throwable)) }
                .map(httpResponseHandler::adaptHttpResponse)
    }
}
