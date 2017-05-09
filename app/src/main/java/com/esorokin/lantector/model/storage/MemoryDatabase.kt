package com.esorokin.lantector.model.storage

import com.esorokin.lantector.model.data.DetectedLanguageText
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryDatabase @Inject constructor() : Database {
    val results: MutableList<DetectedLanguageText> = mutableListOf()

    override fun addDetectedResult(item: DetectedLanguageText): Completable = Completable.fromAction { results.add(item) }

    override fun deleteDetectedResult(item: DetectedLanguageText): Completable = Completable.fromAction { results.remove(item) }

    override fun getAllDetectedResults(): Single<List<DetectedLanguageText>> = Single.just(results)

    override fun removeAllDetectedResults(): Completable = Completable.fromAction { results.clear() }
}