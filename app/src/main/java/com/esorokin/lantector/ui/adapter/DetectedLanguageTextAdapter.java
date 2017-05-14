package com.esorokin.lantector.ui.adapter;

import java.util.List;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.esorokin.lantector.R;
import com.esorokin.lantector.databinding.ItemDetectedLanguageBinding;
import com.esorokin.lantector.model.data.DetectedLanguageText;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

public class DetectedLanguageTextAdapter extends AbsListItemAdapterDelegate<DetectedLanguageText, DisplayableItem, DetectedLanguageTextAdapter.DetectedLanguageHolder> {
	private final LayoutInflater inflater;

	public DetectedLanguageTextAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	protected boolean isForViewType(@NonNull DisplayableItem item, @NonNull List<DisplayableItem> items, int position) {
		return item instanceof DetectedLanguageText;
	}

	@NonNull
	@Override
	protected DetectedLanguageHolder onCreateViewHolder(@NonNull ViewGroup parent) {
		return new DetectedLanguageHolder(DataBindingUtil.inflate(inflater, R.layout.item_detected_language, parent, false));
	}

	@Override
	protected void onBindViewHolder(@NonNull DetectedLanguageText item, @NonNull DetectedLanguageHolder viewHolder, @NonNull List<Object> payloads) {
		viewHolder.viewBinding.languageTextView.setText(item.getLanguage());
		viewHolder.viewBinding.textTextView.setText(item.getText());
	}

	static class DetectedLanguageHolder extends RecyclerView.ViewHolder {
		ItemDetectedLanguageBinding viewBinding;

		public DetectedLanguageHolder(ItemDetectedLanguageBinding viewBinding) {
			super(viewBinding.getRoot());
			this.viewBinding = viewBinding;
		}
	}
}