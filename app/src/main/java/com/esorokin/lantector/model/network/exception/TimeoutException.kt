package com.esorokin.lantector.model.network.exception

import com.esorokin.lantector.model.AppErrorType

class TimeoutException : AppException(AppErrorType.RequestTimeout())
