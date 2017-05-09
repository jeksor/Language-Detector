package com.esorokin.lantector.model.interactor.detect

import com.esorokin.lantector.model.ModelWrapper
import com.esorokin.lantector.model.data.DetectedLanguageText
import com.esorokin.lantector.model.interactor.BaseInteractor
import com.esorokin.lantector.model.service.DetectLanguageService
import com.esorokin.lantector.utils.ext.subscribeIgnoreResult
import com.esorokin.lantector.utils.ext.transitEventsToEmitter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class DetectLanguageInteractor @Inject constructor() : BaseInteractor() {
    @Inject
    internal lateinit var detectLanguageService: DetectLanguageService

    private val detectingLanguageEmitter: Subject<ModelWrapper<DetectedLanguageText>> = PublishSubject.create()

    fun historyEmitter(): Observable<ModelWrapper<List<DetectedLanguageText>>> = detectLanguageService.historyEmitter()

    fun detectingEmitter() : Observable<ModelWrapper<DetectedLanguageText>> = detectingLanguageEmitter

    fun requestHistory() {
        detectLanguageService.requestHistory()
    }

    fun requestDetectLanguage(text: String) {
        detectLanguageService.detectLanguage(text)
                .transitEventsToEmitter(detectingLanguageEmitter)
                .subscribeIgnoreResult()
    }
}