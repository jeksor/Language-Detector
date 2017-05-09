package com.esorokin.lantector.presentation.view.detect

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.esorokin.lantector.presentation.error.UserError
import com.esorokin.lantector.presentation.view.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetectLanguageView : BaseView {
    fun showDetectingProgress()
    fun hideDetectingProgress()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun noInternetConnection()
    fun showError(error: UserError)
    fun hideError()

    fun showDetectingResult(language: String)
    fun hideDetectingResult()
}