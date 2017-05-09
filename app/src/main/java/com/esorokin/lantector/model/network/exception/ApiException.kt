package com.esorokin.lantector.model.network.exception

import com.esorokin.lantector.model.network.data.ApiStatusCode

open class ApiException(val apiStatusCode: ApiStatusCode) : AppException(apiStatusCode.appErrorType)
