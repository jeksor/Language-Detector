package com.esorokin.lantector.model

class ModelWrapper<out Data> private constructor(
        private val state: ModelState,
        val error: Throwable? = null,
        val data: Data? = null
) {
    val isLoading: Boolean
        get() = ModelState.LOADING == state

    val isComplete: Boolean
        get() = ModelState.COMPLETE == state

    val isError: Boolean
        get() = ModelState.ERROR == state && error != null

    private enum class ModelState {
        LOADING,
        COMPLETE,
        ERROR
    }

    companion object {
        fun <T> loading(): ModelWrapper<T> {
            return ModelWrapper(ModelState.LOADING)
        }

        fun <T> error(exception: Throwable): ModelWrapper<T> {
            return ModelWrapper(ModelState.ERROR, exception)
        }

        fun <T> complete(data: T): ModelWrapper<T> {
            return ModelWrapper(state = ModelState.COMPLETE, data = data)
        }
    }
}
