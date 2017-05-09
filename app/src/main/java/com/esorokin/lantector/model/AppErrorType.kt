package com.esorokin.lantector.model

sealed class AppErrorType {
    class NoInternet : AppErrorType()
    class RequestTimeout : AppErrorType()
    class UndefinedError : AppErrorType()
    class NotEnoughText : AppErrorType()
}