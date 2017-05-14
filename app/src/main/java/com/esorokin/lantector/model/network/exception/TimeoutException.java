package com.esorokin.lantector.model.network.exception;

import com.esorokin.lantector.model.AppErrorType;

public class TimeoutException extends AppException {
	public TimeoutException() {
		super(AppErrorType.REQUEST_TIMEOUT);
	}
}
