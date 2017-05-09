package com.esorokin.lantector.model.network.api.handler

import retrofit2.Response

interface HttpResponseAdapter<BaseResponse> {
    fun adaptHttpResponse(response: Response<BaseResponse>): BaseResponse
}
