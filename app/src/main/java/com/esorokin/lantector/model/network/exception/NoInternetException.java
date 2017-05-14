package com.esorokin.lantector.model.network.exception;

import com.esorokin.lantector.model.AppErrorType;

public class NoInternetException extends AppException {
	public NoInternetException() {
		super(AppErrorType.NO_INTERNET);
	}
}
