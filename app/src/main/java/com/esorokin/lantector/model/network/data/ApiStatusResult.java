package com.esorokin.lantector.model.network.data;

import android.text.TextUtils;

public enum ApiStatusResult {
	SUCCESS("OK"),
	ERROR("ERROR");

	private String code;

	ApiStatusResult(String code) {
		this.code = code;
	}

	public static ApiStatusResult from(String code) {
		for (ApiStatusResult apiStatusResult : ApiStatusResult.values()) {
			if (TextUtils.equals(apiStatusResult.code, code)) {
				return apiStatusResult;
			}
		}

		return ERROR;
	}
}
