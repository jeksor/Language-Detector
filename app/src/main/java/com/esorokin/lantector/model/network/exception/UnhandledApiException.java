package com.esorokin.lantector.model.network.exception;

import com.esorokin.lantector.model.network.data.ApiStatusCode;

import lombok.Getter;

@Getter
public class UnhandledApiException extends ApiException {
	private String message;

	public UnhandledApiException(String message) {
		super(ApiStatusCode.UNDEFINED);
		this.message = message;
	}
}
