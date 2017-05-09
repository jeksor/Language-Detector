package com.esorokin.lantector.utils.ext

import com.esorokin.lantector.model.ModelWrapper
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber

private typealias Action = () -> Unit
private typealias ErrorConsumer = (Throwable) -> Unit
private typealias DataConsumer<Data> = (Data) -> Unit

fun <Data> Observable<ModelWrapper<Data>>.consumeBy(
        scheduler: Scheduler = AndroidSchedulers.mainThread(),
        showLoading: Action? = null,
        hideLoading: Action? = null,
        showError: ErrorConsumer? = null,
        hideError: Action? = null,
        receiveData: DataConsumer<Data>? = null
): Disposable = observeOn(scheduler)
        .compose(EventConsumerTransformer(showLoading, hideLoading, showError, hideError, receiveData))
        .subscribe()

private class EventConsumerTransformer<Data>(
        private val showLoading: Action? = null,
        private val hideLoading: Action? = null,
        private val showError: ErrorConsumer? = null,
        private val hideError: Action? = null,
        private val receiveData: DataConsumer<Data>? = null
) : ObservableTransformer<ModelWrapper<Data>, ModelWrapper<Data>> {

    override fun apply(upstream: Observable<ModelWrapper<Data>>): ObservableSource<ModelWrapper<Data>> {
        return upstream.doOnNext { dataModelWrapper ->
            if (dataModelWrapper.isLoading) {
                hideError()
                showLoading()
            }

            if (dataModelWrapper.isError) {
                hideLoading()
                showError(dataModelWrapper.error)
            }

            if (dataModelWrapper.isComplete) {
                hideError()
                hideLoading()
                receiveData(dataModelWrapper.data)
            }
        }
    }

    private fun receiveData(data: Data?) {
        receiveData?.invoke(data!!) ?: Timber.e("EventConsumer receive ModelWrapper.isComplete == true, but receiveData consumer not set.")
    }

    private fun showError(error: Throwable?) {
        showError?.invoke(error!!) ?: Timber.e("EventConsumer receive ModelWrapper.isError == true, but showError consumer not set.")
    }

    private fun hideError() {
        hideError?.invoke() ?: Timber.e("EventConsumer try hide error, but hideError action not set.")
    }

    private fun showLoading() {
        showLoading?.invoke() ?: Timber.e("EventConsumer receive ModelWrapper.isLoading == true, but showLoading action not set.")
    }

    private fun hideLoading() {
        hideLoading?.invoke() ?: Timber.e("EventConsumer try hide loading, but hideLoading action not set.")
    }
}