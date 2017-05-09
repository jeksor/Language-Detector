package com.esorokin.lantector.model.network.api.handler

import retrofit2.Response

internal class InternalCompletableResponseHandler : HttpResponseAdapter<Any> {
    override fun adaptHttpResponse(response: Response<Any>): Any {
        if (response.isSuccessful) {
            return response
        } else {
            throw RuntimeException(response.message() + "")
        }
    }
}
