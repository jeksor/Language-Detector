package com.esorokin.lantector.model.mapper;

import android.support.annotation.NonNull;

/**
 * Simple mapper from one object to other.
 */
public interface Mapper<From, To> {
	To convert(@NonNull From from);
}
