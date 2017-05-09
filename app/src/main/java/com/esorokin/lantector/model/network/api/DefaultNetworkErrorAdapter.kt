package com.esorokin.lantector.model.network.api

import com.esorokin.lantector.model.network.api.handler.NetworkErrorAdapter
import com.esorokin.lantector.model.network.exception.AppException
import com.esorokin.lantector.model.network.exception.NoInternetException
import com.esorokin.lantector.model.network.exception.TimeoutException
import com.esorokin.lantector.model.network.exception.UnhandledApiException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultNetworkErrorAdapter @Inject constructor() : NetworkErrorAdapter {
    override fun adaptNetworkError(throwable: Throwable): AppException {
        Timber.e(throwable)

        return when (throwable) {
            is SocketTimeoutException -> TimeoutException()
            is UnknownHostException -> NoInternetException()
            else -> UnhandledApiException(throwable.localizedMessage)
        }
    }
}
