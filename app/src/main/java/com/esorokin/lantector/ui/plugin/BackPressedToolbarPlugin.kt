package com.esorokin.lantector.ui.plugin

import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View

class BackPressedToolbarPlugin(
        delegate: AppCompatActivity,
        private val backClickListener: () -> Unit,
        @StringRes titleRes: Int = 0,
        title: String? = null) : ToolbarPlugin(delegate, titleRes, title) {

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)
        val supportActionBar = dependency.supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backClickListener.invoke()
    }
}
