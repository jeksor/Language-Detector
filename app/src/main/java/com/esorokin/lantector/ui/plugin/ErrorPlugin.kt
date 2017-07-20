package com.esorokin.lantector.ui.plugin

import com.esorokin.lantector.presentation.error.UserError
import com.esorokin.lantector.ui.plugin.base.BasePlugin

abstract class ErrorPlugin : BasePlugin() {
    abstract fun showUiError(userError: UserError, errorHideListener: () -> Unit = {})

    abstract fun hideUiError()
}
