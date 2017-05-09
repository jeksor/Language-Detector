package com.esorokin.lantector.app

import android.support.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String
}
