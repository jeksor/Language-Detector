package com.esorokin.lantector.utils.ext

import android.support.design.widget.Snackbar
import android.view.View

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit): Snackbar {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
    return snack
}

