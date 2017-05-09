package com.esorokin.lantector.model.network.api.handler

import io.reactivex.Maybe
import io.reactivex.Scheduler
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class MaybeCallAdapter<BaseResponse>(
        private val networkScheduler: Scheduler,
        private val responseType: Type,
        private val networkErrorAdapter: NetworkErrorAdapter,
        private val httpResponseHandler: HttpResponseAdapter<BaseResponse>) : CallAdapter<BaseResponse, Maybe<BaseResponse>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<BaseResponse>): Maybe<BaseResponse> {
        return CallExecuteObservable(call).singleElement()
                .subscribeOn(networkScheduler)
                .onErrorResumeNext { throwable: Throwable -> Maybe.error(networkErrorAdapter.adaptNetworkError(throwable)) }
                .map(httpResponseHandler::adaptHttpResponse)
    }
}
