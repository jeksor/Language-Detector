package com.esorokin.lantector.ui.plugin

import android.app.ProgressDialog
import android.content.Context
import com.esorokin.lantector.R
import com.esorokin.lantector.ui.plugin.base.BasePlugin

class ProgressPlugin(private val contextProvider: () -> Context) : BasePlugin() {
    private var dialog: ProgressDialog? = null

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }

    fun showProgress() {
        hideProgress()
        dialog = ProgressDialog(contextProvider.invoke()).apply {
            isIndeterminate = true
            setCancelable(true)
            setCanceledOnTouchOutside(false)
            setMessage(context.getString(R.string.loading))
            show()
        }
    }

    fun hideProgress() {
        dialog?.dismiss()
    }
}
