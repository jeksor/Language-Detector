package com.esorokin.lantector.model.service

import com.esorokin.lantector.model.ModelWrapper
import com.esorokin.lantector.model.data.DetectedLanguageText
import com.esorokin.lantector.model.mapper.DetectLanguageTextMapper
import com.esorokin.lantector.model.network.api.WatsonPlatformAdi
import com.esorokin.lantector.model.storage.Database
import com.esorokin.lantector.utils.ext.subscribeIgnoreResult
import com.esorokin.lantector.utils.ext.transitSuccessToEmitter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetectLanguageService @Inject constructor() {
    @Inject
    internal lateinit var watsonPlatformAdi: WatsonPlatformAdi

    @Inject
    internal lateinit var detectedTextMapper: DetectLanguageTextMapper

    @Inject
    internal lateinit var database: Database

    private val historyEmitter: Subject<ModelWrapper<List<DetectedLanguageText>>> = PublishSubject.create()

    fun historyEmitter(): Observable<ModelWrapper<List<DetectedLanguageText>>> = historyEmitter

    fun requestHistory() {
        getHistory().subscribeIgnoreResult()
    }

    fun getHistory(): Single<List<DetectedLanguageText>> {
        return database.getAllDetectedResults()
                .map { it.sortedByDescending { it.date } }
                .transitSuccessToEmitter(historyEmitter)
    }

    fun detectLanguage(text: String): Single<DetectedLanguageText> = watsonPlatformAdi.detectLanguage(text)
            .map { detectedTextMapper.invoke(it, text) }
            .doOnSuccess {
                database.addDetectedResult(it)
                        .subscribeIgnoreResult()

                requestHistory()
            }
}
