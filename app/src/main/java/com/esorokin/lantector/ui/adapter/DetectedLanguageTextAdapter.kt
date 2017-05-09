package com.esorokin.lantector.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esorokin.lantector.R
import com.esorokin.lantector.model.data.DetectedLanguageText
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_detected_language.view.*

class DetectedLanguageTextAdapter(val context: Context) : AbsListItemAdapterDelegate<DetectedLanguageText, DisplayableItem, DetectedLanguageTextAdapter.DetectedLanguageHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun isForViewType(item: DisplayableItem, items: MutableList<DisplayableItem>, position: Int): Boolean = item is DetectedLanguageText

    override fun onBindViewHolder(item: DetectedLanguageText, viewHolder: DetectedLanguageHolder, payloads: MutableList<Any>) {
        viewHolder.itemView.languageTextView.text = item.language
        viewHolder.itemView.textTextView.text = item.text
    }

    override fun onCreateViewHolder(parent: ViewGroup): DetectedLanguageHolder {
        return DetectedLanguageHolder(inflater.inflate(R.layout.item_detected_language, parent, false))
    }

    class DetectedLanguageHolder(view: View): RecyclerView.ViewHolder(view)
}