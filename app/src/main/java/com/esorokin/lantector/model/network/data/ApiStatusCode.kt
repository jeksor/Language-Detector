package com.esorokin.lantector.model.network.data

import com.esorokin.lantector.model.AppErrorType

enum class ApiStatusCode(val code: String = "",
                         val appErrorType: AppErrorType = AppErrorType.UndefinedError()) {
    UNDEFINED,

    NOT_ENOUGH_TEXT("not-enough-text-for-language-id", AppErrorType.NotEnoughText());

    companion object {
        fun from(code: String): ApiStatusCode {
            ApiStatusCode.values().forEach { if (it.code == code) return it }
            return UNDEFINED
        }
    }
}
