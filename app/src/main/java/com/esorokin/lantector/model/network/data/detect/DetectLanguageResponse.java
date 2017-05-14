package com.esorokin.lantector.model.network.data.detect;

import com.esorokin.lantector.model.network.data.BaseResponse;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Getter;

@Root(name = "results", strict = false)
@Getter
public class DetectLanguageResponse extends BaseResponse {
	@Element(name = "language", required = false)
	private String language;
}
