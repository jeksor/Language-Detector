package com.esorokin.lantector.model.storage

import com.esorokin.lantector.model.data.DetectedLanguageText
import io.reactivex.Completable
import io.reactivex.Single
import io.requery.Persistable
import io.requery.reactivex.KotlinReactiveEntityStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersistentDatabase @Inject constructor() : Database {
    @Inject
    lateinit var store: KotlinReactiveEntityStore<Persistable>

    override fun addDetectedResult(item: DetectedLanguageText): Completable = store.insert(item).toCompletable()

    override fun deleteDetectedResult(item: DetectedLanguageText): Completable = store.delete(item)

    override fun getAllDetectedResults(): Single<List<DetectedLanguageText>> = (store select DetectedLanguageText::class).get().observable().toList()

    override fun removeAllDetectedResults(): Completable = store.delete(DetectedLanguageText::class).get().single().toCompletable()
}