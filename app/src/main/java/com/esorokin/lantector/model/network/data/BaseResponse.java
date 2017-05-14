package com.esorokin.lantector.model.network.data;

import org.simpleframework.xml.Element;

public class BaseResponse {
	@Element(name = "status")
	private String statusResult;

	@Element(name = "statusInfo", required = false)
	private String statusCode;

	protected BaseResponse() {
		//not available for directly create
	}

	public ApiStatusCode apiStatusCode() {
		return ApiStatusCode.from(statusCode);
	}

	public ApiStatusResult apiStatusResult() {
		return ApiStatusResult.from(statusResult);
	}
}
