package com.esorokin.lantector.model.network.api

import com.esorokin.lantector.model.network.data.detectlanguage.DetectLanguageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WatsonPlatformAdi {
    @GET("calls/text/TextGetLanguage")
    fun detectLanguage(@Query("text") text: String,
                       @Query("apikey") apiKey: String = "4978e60252ae102dfe1341146bb8cc3ec4bbbd78"): Single<DetectLanguageResponse>
}
