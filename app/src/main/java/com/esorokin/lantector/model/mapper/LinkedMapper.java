package com.esorokin.lantector.model.mapper;

import android.support.annotation.NonNull;

/**
 * Mapper from one object to other with 3rd dependency object.
 */
public interface LinkedMapper<From, To, Dependency> {
	To convert(@NonNull From from, @NonNull Dependency dependency);
}
