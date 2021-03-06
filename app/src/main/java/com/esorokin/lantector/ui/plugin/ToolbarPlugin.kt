package com.esorokin.lantector.ui.plugin

import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import com.esorokin.lantector.ui.plugin.base.BasePlugin

open class ToolbarPlugin(protected val activityProvider: () -> AppCompatActivity,
                         @StringRes val titleRes: Int = 0,
                         private var title: String? = null) : BasePlugin() {
    private val toolbar: Toolbar? by lazy { getToolbar(activityProvider.invoke().window.decorView) }

    override fun onViewCreated(view: View) {
        super.onViewCreated(view)

        val activity = activityProvider.invoke()

        if (title.isNullOrEmpty().and(titleRes != 0)) {
            title = activity.getString(titleRes)
        }

        toolbar?.let {
            if (activity.supportActionBar == null) {
                activity.setSupportActionBar(it)
            }

            if (!title.isNullOrEmpty()) {
                it.title = title
                activity.title = title
            }
        }
    }

    fun changeTitle(title: String) {
        this.title = title
        toolbar?.title = title
        activityProvider.invoke().title = title
    }

    private fun getToolbar(view: View): Toolbar? {
        if (view is Toolbar) {
            return view
        }

        if (view is ViewGroup) {
            (0..view.childCount - 1)
                    .mapNotNull { getToolbar(view.getChildAt(it)) }
                    .forEach { return it }
        }

        return null
    }
}
