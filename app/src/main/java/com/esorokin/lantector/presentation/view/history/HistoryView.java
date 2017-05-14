package com.esorokin.lantector.presentation.view.history;

import java.util.List;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.esorokin.lantector.model.data.DetectedLanguageText;
import com.esorokin.lantector.presentation.error.UserError;
import com.esorokin.lantector.presentation.view.BaseView;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HistoryView extends BaseView {
	void showError(UserError error);

	void hideError();

	void showItems(List<DetectedLanguageText> items);

	void hideItems();
}
