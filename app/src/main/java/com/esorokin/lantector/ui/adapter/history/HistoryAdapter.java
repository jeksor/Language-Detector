package com.esorokin.lantector.ui.adapter.history;

import java.util.List;

import android.content.Context;

import com.esorokin.lantector.ui.adapter.DetectedLanguageTextAdapter;
import com.esorokin.lantector.ui.adapter.DisplayableItem;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

public class HistoryAdapter extends ListDelegationAdapter<List<DisplayableItem>> {
	public HistoryAdapter(Context context) {
		delegatesManager.addDelegate(new DetectedLanguageTextAdapter(context));
	}
}