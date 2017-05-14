package com.esorokin.lantector.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.inputmethod.InputMethodManager;

public final class ViewUtils {
	private static final float DENSITY_FACTOR = 160f;

	private ViewUtils() {
		//utils
	}

	public static float pxToDp(float px) {
		float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
		return px / (densityDpi / DENSITY_FACTOR);
	}

	public static int dpToPx(int dp) {
		float density = Resources.getSystem().getDisplayMetrics().density;
		return Math.round(dp * density);
	}

	public static void hideKeyboard(Activity activity) {
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
	}
}
