package com.esorokin.lantector.utils.ext

import com.esorokin.lantector.model.AppErrorType
import com.esorokin.lantector.model.network.exception.AppException

fun Throwable.toAppException(): AppException = this as? AppException ?: AppException(AppErrorType.UndefinedError())