package com.esorokin.lantector.ui.fragment.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.esorokin.lantector.R;
import com.esorokin.lantector.databinding.FragmentHistoryBinding;
import com.esorokin.lantector.model.data.DetectedLanguageText;
import com.esorokin.lantector.presentation.error.UserError;
import com.esorokin.lantector.presentation.presenter.history.HistoryPresenter;
import com.esorokin.lantector.presentation.view.history.HistoryView;
import com.esorokin.lantector.ui.adapter.history.HistoryAdapter;
import com.esorokin.lantector.ui.fragment.BaseFragment;
import com.esorokin.lantector.ui.plugins.DialogErrorPlugin;
import com.esorokin.lantector.ui.plugins.ErrorPlugin;
import com.esorokin.lantector.ui.plugins.ToolbarPlugin;

public class HistoryFragment extends BaseFragment implements HistoryView {
	@InjectPresenter
	HistoryPresenter presenter;

	private ErrorPlugin errorPlugin;

	private HistoryAdapter adapter;
	private FragmentHistoryBinding viewBinding;

	@Override
	protected void initPlugins() {
		super.initPlugins();
		compositionPlugin().attach(new ToolbarPlugin((AppCompatActivity) getActivity(), R.string.screen_title_history));
		compositionPlugin().attach(errorPlugin = new DialogErrorPlugin(this::getContext));
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
		return viewBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		adapter = new HistoryAdapter(getContext());
		viewBinding.historyListView.setAdapter(adapter);
		viewBinding.historyListView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
		viewBinding.historyListView.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
	}

	@Override
	public void showError(UserError error) {
		errorPlugin.showUiError(error, () -> presenter.userHideError());
	}

	@Override
	public void hideError() {
		errorPlugin.hideUiError();
	}

	@Override
	public void showItems(List<DetectedLanguageText> items) {
		adapter.setItems(new ArrayList<>(items));
		adapter.notifyDataSetChanged();
		viewBinding.historyListView.setVisibility(View.VISIBLE);
		viewBinding.emptyImageView.setVisibility(View.GONE);
		viewBinding.emptyTextView.setVisibility(View.GONE);
	}

	@Override
	public void hideItems() {
		adapter.setItems(Collections.emptyList());
		adapter.notifyDataSetChanged();
		viewBinding.historyListView.setVisibility(View.GONE);
		viewBinding.emptyImageView.setVisibility(View.VISIBLE);
		viewBinding.emptyTextView.setVisibility(View.VISIBLE);
	}
}
