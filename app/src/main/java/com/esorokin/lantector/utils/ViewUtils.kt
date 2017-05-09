package com.esorokin.lantector.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.inputmethod.InputMethodManager

object ViewUtils {
    private const val DENSITY_FACTOR = 160f

    fun pxToDp(px: Float): Float {
        val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
        return px / (densityDpi / DENSITY_FACTOR)
    }

    fun dpToPx(dp: Int): Int {
        val density = Resources.getSystem().displayMetrics.density
        return Math.round(dp * density)
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
    }
}
