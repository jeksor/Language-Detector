package com.esorokin.lantector.model.network.data

import org.simpleframework.xml.Element

open class BaseResponse(
        @field:Element(name = "status")
        var statusResult: String = "ERROR",

        @field:Element(name = "statusInfo", required = false)
        var statusCode: String = "") {

    fun apiStatusResult(): ApiStatusResult = ApiStatusResult.from(statusResult)

    fun apiStatusCode(): ApiStatusCode = ApiStatusCode.from(statusCode)
}
