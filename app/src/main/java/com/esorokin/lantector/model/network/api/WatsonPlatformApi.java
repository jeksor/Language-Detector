package com.esorokin.lantector.model.network.api;

import com.esorokin.lantector.model.network.data.detect.DetectLanguageResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WatsonPlatformApi {
	String API_KEY = "4978e60252ae102dfe1341146bb8cc3ec4bbbd78";

	@GET("calls/text/TextGetLanguage")
	Single<DetectLanguageResponse> detectLanguage(@Query("text") String text, @Query("apikey") String apiKey);
}
