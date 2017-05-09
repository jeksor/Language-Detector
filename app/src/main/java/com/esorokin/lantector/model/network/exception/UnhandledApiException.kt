package com.esorokin.lantector.model.network.exception

import com.esorokin.lantector.model.network.data.ApiStatusCode

class UnhandledApiException(message: String) : ApiException(ApiStatusCode.UNDEFINED) {
    constructor(exception: Exception) : this(exception.localizedMessage)
}
