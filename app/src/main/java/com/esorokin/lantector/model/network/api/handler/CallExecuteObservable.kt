/*
 * Copyright (C) 2016 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.esorokin.lantector.model.network.api.handler

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.plugins.RxJavaPlugins
import retrofit2.Call
import retrofit2.Response

internal class CallExecuteObservable<T>(private val originalCall: Call<T>) : Observable<Response<T>>() {

    override fun subscribeActual(observer: Observer<in Response<T>>) {
        // Since Call is a one-shot type, clone it for each new observer.
        val call = originalCall.clone()
        observer.onSubscribe(CallDisposable(call))

        var terminated = false
        try {
            val response = call.execute()
            if (!call.isCanceled) {
                observer.onNext(response)
            }
            if (!call.isCanceled) {
                terminated = true
                observer.onComplete()
            }
        } catch (t: Throwable) {
            Exceptions.throwIfFatal(t)
            if (terminated) {
                RxJavaPlugins.onError(t)
            } else if (!call.isCanceled) {
                try {
                    observer.onError(t)
                } catch (inner: Throwable) {
                    Exceptions.throwIfFatal(inner)
                    RxJavaPlugins.onError(CompositeException(t, inner))
                }
            }
        }
    }

    private class CallDisposable internal constructor(private val call: Call<*>) : Disposable {
        override fun dispose() {
            call.cancel()
        }

        override fun isDisposed(): Boolean {
            return call.isCanceled
        }
    }
}
