package com.esorokin.lantector.model.network.data.detectlanguage

import com.esorokin.lantector.model.network.data.BaseResponse
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "results", strict = false)
class DetectLanguageResponse(
        @field:Element(name = "language", required = false)
        var language: String = "russian") : BaseResponse()