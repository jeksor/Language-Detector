package com.esorokin.lantector.presentation.presenter.detect

import com.arellomobile.mvp.InjectViewState
import com.esorokin.lantector.di.DependencyManager
import com.esorokin.lantector.model.AppErrorType
import com.esorokin.lantector.model.interactor.detect.DetectLanguageInteractor
import com.esorokin.lantector.presentation.error.ErrorProcessor
import com.esorokin.lantector.presentation.presenter.BasePresenter
import com.esorokin.lantector.presentation.view.detect.DetectLanguageView
import com.esorokin.lantector.utils.ext.autoDispose
import com.esorokin.lantector.utils.ext.consumeBy
import com.esorokin.lantector.utils.ext.toAppException
import javax.inject.Inject

@InjectViewState
class DetectLanguagePresenter : BasePresenter<DetectLanguageView>() {
    @Inject
    internal lateinit var interactor: DetectLanguageInteractor

    @Inject
    internal lateinit var errorProcessor: ErrorProcessor

    init {
        DependencyManager.appComponent.inject(this)

        interactor.detectingEmitter()
                .consumeBy(
                        showLoading = { viewState.showDetectingProgress() },
                        hideLoading = { viewState.hideDetectingProgress() },
                        showError = handleDetectingError(),
                        hideError = { viewState.hideError() },
                        receiveData = { viewState.showDetectingResult(it.language) }
                )
                .autoDispose(this)

        interactor.autoDispose(this)
    }

    private fun handleDetectingError(): (Throwable) -> Unit = {
        val appException = it.toAppException()
        when (appException.appErrorType) {
            is AppErrorType.NoInternet -> viewState.noInternetConnection()
            else -> viewState.showError(errorProcessor.processError(it))
        }
    }

    fun userClickStartDetect(textToDetect: String) {
        interactor.requestDetectLanguage(textToDetect)
    }

    fun userClickRetryDetect(textToDetect: String) {
        interactor.requestDetectLanguage(textToDetect)
    }

    fun userHideDetectingResult() {
        viewState.hideDetectingResult()
    }

    fun userHideError() {
        viewState.hideError()
    }
}
