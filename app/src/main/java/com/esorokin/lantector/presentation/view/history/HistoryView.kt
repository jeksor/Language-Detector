package com.esorokin.lantector.presentation.view.history

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.esorokin.lantector.model.data.DetectedLanguageText
import com.esorokin.lantector.presentation.error.UserError
import com.esorokin.lantector.presentation.view.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface HistoryView : BaseView {
    fun showError(error: UserError)
    fun hideError()

    fun showItems(items: List<DetectedLanguageText>)
    fun hideItems()
}