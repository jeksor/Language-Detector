package com.esorokin.lantector.model.network.api.handler

import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RxCallAdapterFactory private constructor(
        private val baseResponseType: Class<*>,
        private val networkErrorAdapter: NetworkErrorAdapter,
        private val httpResponseAdapter: HttpResponseAdapter<*>,
        private val networkScheduler: Scheduler = Schedulers.io()
) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val rawType = getRawType(returnType)

        val isCompletable = rawType == Comparable::class.java
        val isFlowable = rawType == Flowable::class.java
        val isSingle = rawType == Single::class.java
        val isMaybe = rawType == Maybe::class.java

        if (isCompletable) {
            return CompletableCallAdapter(networkScheduler, networkErrorAdapter, InternalCompletableResponseHandler())
        }

        if (!isFlowable && !isSingle && !isMaybe && rawType != Observable::class.java) {
            return null
        }

        if (returnType !is ParameterizedType) {
            val rxName = rawType.simpleName
            val message = "$rxName return type must be parametrized as $rxName<Foo> or $rxName<? extends Foo>"
            throw IllegalStateException(message)
        }

        val responseType = getRawType(getParameterUpperBound(0, returnType))
        if (!baseResponseType.isAssignableFrom(responseType)) {
            return null
        }

        if (isSingle) {
            return SingleCallAdapter(networkScheduler, responseType, networkErrorAdapter, httpResponseAdapter)

        } else if (isMaybe) {
            return MaybeCallAdapter(networkScheduler, responseType, networkErrorAdapter, httpResponseAdapter)

        } else if (isFlowable) {
            return FlowableCallAdapter(networkScheduler, responseType, networkErrorAdapter, httpResponseAdapter)

        } else {
            return ObservableCallAdapter(networkScheduler, responseType, networkErrorAdapter, httpResponseAdapter)
        }
    }

    companion object Factory {
        /**
         * Create call adapter factory for your response type and it's child's.<br></br>
         * You can register plenty of factories for separate child. Just register in right order.<br></br>
         * For example:<br></br>
         * Child1<br></br>
         * Child2<br></br>
         * Parent<br></br>

         * @param baseResponseType    Target response type
         * *
         * @param networkErrorAdapter Handler network, converting and other non-http errors
         * *
         * @param httpResponseAdapter Handler server response (fail, success, status etc)
         */
        fun <R> create(baseResponseType: Class<R>,
                       networkErrorAdapter: NetworkErrorAdapter,
                       httpResponseAdapter: HttpResponseAdapter<R>): RxCallAdapterFactory {
            return RxCallAdapterFactory(baseResponseType, networkErrorAdapter, httpResponseAdapter)
        }
    }
}
