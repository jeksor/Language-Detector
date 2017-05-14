package com.esorokin.lantector.model.network.api.handler;

import retrofit2.Response;

class InternalCompletableResponseHandler implements HttpResponseAdapter<Object> {
	@Override
	public Object adaptHttpResponse(Response<Object> response) {
		if (response.isSuccessful()) {
			return response;
		} else {
			throw new RuntimeException(response.message() + "");
		}
	}
}
