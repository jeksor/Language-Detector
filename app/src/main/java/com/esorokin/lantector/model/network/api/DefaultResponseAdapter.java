package com.esorokin.lantector.model.network.api;

import com.esorokin.lantector.model.network.api.handler.HttpResponseAdapter;
import com.esorokin.lantector.model.network.data.ApiStatusResult;
import com.esorokin.lantector.model.network.data.BaseResponse;
import com.esorokin.lantector.model.network.exception.ApiException;
import com.esorokin.lantector.model.network.exception.UnhandledApiException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class DefaultResponseAdapter implements HttpResponseAdapter<BaseResponse> {
	@Inject
	public DefaultResponseAdapter() {
		//inject
	}

	@Override
	public BaseResponse adaptHttpResponse(Response<BaseResponse> response) throws ApiException {
		if (response.isSuccessful()) {
			if (response.body().apiStatusResult().equals(ApiStatusResult.SUCCESS)) {
				return response.body();
			} else {
				throw new ApiException(response.body().apiStatusCode());
			}

		} else {
			throw new UnhandledApiException(response.message());
		}
	}
}
