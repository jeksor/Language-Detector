package com.esorokin.lantector.model.network.data;

import android.text.TextUtils;

import com.esorokin.lantector.model.AppErrorType;

public enum ApiStatusCode {
	UNDEFINED("", AppErrorType.UNDEFINED_ERROR),

	NOT_ENOUGH_TEXT("not-enough-text-for-language-id", AppErrorType.NOT_ENOUGH_TEXT);

	public String code;
	public AppErrorType appErrorType;

	ApiStatusCode(String code, AppErrorType appErrorType) {
		this.code = code;
		this.appErrorType = appErrorType;
	}

	public static ApiStatusCode from(String code) {
		for (ApiStatusCode apiStatusCode : ApiStatusCode.values()) {
			if (TextUtils.equals(apiStatusCode.code, code)) {
				return apiStatusCode;
			}
		}

		return UNDEFINED;
	}
}
