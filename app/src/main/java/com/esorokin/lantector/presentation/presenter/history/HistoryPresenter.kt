package com.esorokin.lantector.presentation.presenter.history

import com.arellomobile.mvp.InjectViewState
import com.esorokin.lantector.di.DependencyManager
import com.esorokin.lantector.model.data.DetectedLanguageText
import com.esorokin.lantector.model.interactor.detect.DetectLanguageInteractor
import com.esorokin.lantector.presentation.error.ErrorProcessor
import com.esorokin.lantector.presentation.presenter.BasePresenter
import com.esorokin.lantector.presentation.view.history.HistoryView
import com.esorokin.lantector.utils.ext.autoDispose
import com.esorokin.lantector.utils.ext.consumeBy
import javax.inject.Inject

@InjectViewState
class HistoryPresenter : BasePresenter<HistoryView>() {
    @Inject
    internal lateinit var interactor: DetectLanguageInteractor

    @Inject
    internal lateinit var errorProcessor: ErrorProcessor

    init {
        DependencyManager.appComponent.inject(this)

        interactor.historyEmitter()
                .consumeBy(
                        showError = { viewState.showError(errorProcessor.processError(it)) },
                        hideError = { viewState.hideError() },
                        receiveData = handleItems()
                )
                .autoDispose(this)

        interactor.requestHistory()
        interactor.autoDispose(this)
    }

    private fun handleItems(): (List<DetectedLanguageText>) -> Unit = {
        if (it.isEmpty()) {
            viewState.hideItems()
        } else {
            viewState.showItems(it)
        }
    }

    fun userHideError() {
        viewState.hideError()
    }
}
