package com.esorokin.lantector.ui.plugin

import android.content.Context
import android.support.v7.app.AlertDialog
import com.esorokin.lantector.R
import com.esorokin.lantector.ui.plugin.base.BaseDependencyPlugin

class AlertMessagePlugin(context: Context) : BaseDependencyPlugin<Context>(context) {
    private var dialog: AlertDialog? = null

    override fun onDestroy() {
        super.onDestroy()
        hideMessage()
    }

    fun showMessage(message: String? = null,
                    title: String? = null,
                    okText: String = dependency.getString(R.string.ok),
                    hideListener: () -> Unit = {}) {
        hideMessage()

        val builder = AlertDialog.Builder(dependency)

        title?.let { builder.setTitle(it) }
        message?.let { builder.setMessage(it) }
        builder.setPositiveButton(okText, { _, _ -> hideListener.invoke() })
        builder.setCancelable(true)
        builder.setOnCancelListener { hideListener.invoke() }

        dialog = builder.show()
    }

    fun hideMessage() {
        dialog?.dismiss()
    }
}