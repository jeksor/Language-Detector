package com.esorokin.lantector.presentation.view.detect;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.esorokin.lantector.presentation.error.UserError;
import com.esorokin.lantector.presentation.view.BaseView;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface DetectLanguageView extends BaseView {
	void showDetectingProgress();

	void hideDetectingProgress();

	@StateStrategyType(OneExecutionStateStrategy.class)
	void noInternetConnection();

	void showError(UserError error);

	void hideError();

	void showDetectingResult(String language);

	void hideDetectingResult();
}
