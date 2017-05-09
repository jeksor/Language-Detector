package com.esorokin.lantector.ui.plugin

import android.app.ProgressDialog
import android.content.Context

import com.esorokin.lantector.R
import com.esorokin.lantector.ui.plugin.base.BaseDependencyPlugin

class ProgressPlugin(delegate: Context) : BaseDependencyPlugin<Context>(delegate) {
    private var dialog: ProgressDialog? = null

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }

    fun showProgress() {
        hideProgress()
        dialog = ProgressDialog(dependency).apply {
            isIndeterminate = true
            setCancelable(true)
            setCanceledOnTouchOutside(false)
            setMessage(dependency.getString(R.string.loading))
            show()
        }
    }

    fun hideProgress() {
        dialog?.dismiss()
    }
}
