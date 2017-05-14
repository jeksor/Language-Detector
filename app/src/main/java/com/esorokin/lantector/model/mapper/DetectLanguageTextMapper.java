package com.esorokin.lantector.model.mapper;

import java.util.Date;

import android.support.annotation.NonNull;

import com.esorokin.lantector.model.data.DetectedLanguageText;
import com.esorokin.lantector.model.data.DetectedLanguageTextEntity;
import com.esorokin.lantector.model.network.data.detect.DetectLanguageResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DetectLanguageTextMapper implements LinkedMapper<DetectLanguageResponse, DetectedLanguageText, String> {
	@Inject
	public DetectLanguageTextMapper() {
		//inject
	}

	@Override
	public DetectedLanguageText convert(@NonNull DetectLanguageResponse detectLanguageResponse, @NonNull String text) {
		DetectedLanguageTextEntity entity = new DetectedLanguageTextEntity();
		entity.setText(text);
		entity.setDate(new Date());
		entity.setLanguage(detectLanguageResponse.getLanguage());
		return entity;
	}
}
