package com.esorokin.lantector.model.network.exception

import com.esorokin.lantector.model.AppErrorType

open class AppException(val appErrorType: AppErrorType) : RuntimeException()