package com.esorokin.lantector.presentation.error

/**
 * Using by presenters for localize domain exceptions into user friendly messages.
 */
interface ErrorProcessor {
    fun processError(throwable: Throwable): UserError
}
