package com.esorokin.lantector.ui.plugin

import android.content.Context
import com.esorokin.lantector.presentation.error.UserError
import com.esorokin.lantector.ui.plugin.base.BaseDependencyPlugin

abstract class ErrorPlugin(dependency: Context) : BaseDependencyPlugin<Context>(dependency) {
    abstract fun showUiError(userError: UserError, errorHideListener: () -> Unit = {})

    abstract fun hideUiError()
}
