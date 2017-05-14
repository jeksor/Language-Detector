package com.esorokin.lantector.utils;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
	public static long timeZoneOffsetHours() {
		return TimeUnit.HOURS.convert(TimeZone.getDefault().getRawOffset(), TimeUnit.MILLISECONDS);
	}
}
