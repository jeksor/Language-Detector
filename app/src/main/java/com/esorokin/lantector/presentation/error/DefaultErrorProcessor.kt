package com.esorokin.lantector.presentation.error

import com.esorokin.lantector.R
import com.esorokin.lantector.app.StringProvider
import com.esorokin.lantector.model.AppErrorType
import com.esorokin.lantector.model.network.data.ApiStatusCode
import com.esorokin.lantector.model.network.exception.ApiException
import com.esorokin.lantector.model.network.exception.AppException
import com.esorokin.lantector.utils.ext.toAppException
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultErrorProcessor @Inject constructor(private val stringProvider: StringProvider) : ErrorProcessor {
    override fun processError(throwable: Throwable): UserError {
        return when(throwable.toAppException().appErrorType) {
            is AppErrorType.NoInternet -> UserError(stringProvider.getString(R.string.error_default_title), stringProvider.getString(R.string.error_internet_connection))
            is AppErrorType.RequestTimeout -> UserError(stringProvider.getString(R.string.error_default_title), stringProvider.getString(R.string.error_timeout))
            is AppErrorType.UndefinedError -> handleUnknownException(throwable)
            else -> handleApiException(throwable.toAppException())
        }
    }

    private fun handleApiException(appException: AppException): UserError {
        if (appException is ApiException) {
            return when(appException.apiStatusCode) {
                ApiStatusCode.NOT_ENOUGH_TEXT -> UserError(stringProvider.getString(R.string.error_default_title), "Not enough text length!")
                else -> handleUnknownException(appException)
            }
        } else {
            return handleUnknownException(appException)
        }
    }

    private fun handleUnknownException(unknown: Throwable): UserError {
        Timber.w("Unknown exception! Maybe something wrong with this app.")
        Timber.w(unknown)

        return UserError(stringProvider.getString(R.string.error_unknown_title), stringProvider.getString(R.string.error_unknown_message))
    }
}
