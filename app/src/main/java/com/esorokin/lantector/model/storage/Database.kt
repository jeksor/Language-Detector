package com.esorokin.lantector.model.storage

import com.esorokin.lantector.model.data.DetectedLanguageText
import io.reactivex.Completable
import io.reactivex.Single

interface Database {
    fun addDetectedResult(item: DetectedLanguageText): Completable
    fun deleteDetectedResult(item: DetectedLanguageText): Completable

    fun getAllDetectedResults(): Single<List<DetectedLanguageText>>
    fun removeAllDetectedResults(): Completable
}