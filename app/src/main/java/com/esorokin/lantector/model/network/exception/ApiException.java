package com.esorokin.lantector.model.network.exception;

import com.esorokin.lantector.model.network.data.ApiStatusCode;

public class ApiException extends AppException {
	public ApiException(ApiStatusCode apiStatusCode) {
		super(apiStatusCode.appErrorType);
	}
}
