package com.esorokin.lantector.model.mapper

import com.esorokin.lantector.model.data.DetectedLanguageText
import com.esorokin.lantector.model.network.data.detectlanguage.DetectLanguageResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetectLanguageTextMapper @Inject constructor() : (DetectLanguageResponse, String) -> DetectedLanguageText {
    override fun invoke(response: DetectLanguageResponse, text: String): DetectedLanguageText = DetectedLanguageText(text, response.language)
}

fun <From, To> convertCollection(fromCollection: Collection<From>?, itemMapper: (From) -> To?): List<To> {
    val result = mutableListOf<To>()

    if (fromCollection != null) {
        for (fromItem in fromCollection) {
            itemMapper.invoke(fromItem)?.let {
                result.add(it)
            }
        }
    }

    return result
}

fun <From, To, Dependency> convertCollection(fromCollection: Collection<From>?,
                                             dependency: Dependency,
                                             itemMapper: (From, Dependency) -> To?): List<To> {
    val result = mutableListOf<To>()

    if (fromCollection != null) {
        for (fromItem in fromCollection) {
            itemMapper.invoke(fromItem, dependency)?.let {
                result.add(it)
            }
        }
    }

    return result
}

