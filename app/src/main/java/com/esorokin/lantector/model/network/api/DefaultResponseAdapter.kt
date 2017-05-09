package com.esorokin.lantector.model.network.api

import com.esorokin.lantector.model.network.api.handler.HttpResponseAdapter
import com.esorokin.lantector.model.network.data.ApiStatusResult
import com.esorokin.lantector.model.network.data.BaseResponse
import com.esorokin.lantector.model.network.exception.ApiException
import com.esorokin.lantector.model.network.exception.UnhandledApiException
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultResponseAdapter @Inject constructor() : HttpResponseAdapter<BaseResponse> {
    @Throws(ApiException::class)
    override fun adaptHttpResponse(response: Response<BaseResponse>): BaseResponse {
        if (response.isSuccessful) {
            if (response.body().apiStatusResult() == ApiStatusResult.SUCCESS) {
                return response.body()
            } else {
                throw ApiException(response.body().apiStatusCode())
            }

        } else {
            throw UnhandledApiException(response.message())
        }
    }
}
