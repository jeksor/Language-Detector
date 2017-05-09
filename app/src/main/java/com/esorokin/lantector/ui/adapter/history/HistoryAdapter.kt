package com.esorokin.lantector.ui.adapter.history

import android.content.Context
import com.esorokin.lantector.ui.adapter.DetectedLanguageTextAdapter
import com.esorokin.lantector.ui.adapter.DisplayableItem
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class HistoryAdapter(context: Context) : ListDelegationAdapter<List<DisplayableItem>>() {
    init {
        delegatesManager.addDelegate(DetectedLanguageTextAdapter(context))
    }
}