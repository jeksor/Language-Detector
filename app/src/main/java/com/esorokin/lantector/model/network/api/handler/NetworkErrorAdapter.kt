package com.esorokin.lantector.model.network.api.handler

import com.esorokin.lantector.model.network.exception.AppException

interface NetworkErrorAdapter {
    fun adaptNetworkError(throwable: Throwable): AppException
}
