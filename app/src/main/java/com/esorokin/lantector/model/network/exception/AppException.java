package com.esorokin.lantector.model.network.exception;

import com.esorokin.lantector.model.AppErrorType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
	private AppErrorType appErrorType;
}
