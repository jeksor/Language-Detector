package com.esorokin.lantector.ui.plugin

import android.content.Context
import android.support.v7.app.AlertDialog

import com.esorokin.lantector.R
import com.esorokin.lantector.presentation.error.UserError

class DialogErrorPlugin(private val context: Context) : ErrorPlugin() {
    private var dialog: AlertDialog? = null

    override fun onDestroy() {
        super.onDestroy()
        hideUiError()
    }

    override fun showUiError(userError: UserError, errorHideListener: () -> Unit) {
        hideUiError()
        dialog = AlertDialog.Builder(context)
                .setTitle(userError.title)
                .setMessage(userError.message)
                .setCancelable(true)
                .setPositiveButton(R.string.ok) { _, _ -> errorHideListener.invoke() }
                .show()
    }

    override fun hideUiError() {
        dialog?.dismiss()
    }
}
