package com.esorokin.lantector.model.data

import com.esorokin.lantector.ui.adapter.DisplayableItem
import io.requery.Entity
import io.requery.Persistable
import java.util.*

@Entity
data class DetectedLanguageText(val text: String = "", val language: String = "", val date: Date = Date()) : DisplayableItem, Persistable