package com.esorokin.lantector.ui.plugins;

import android.support.annotation.Nullable;

import com.esorokin.lantector.presentation.error.UserError;
import com.esorokin.lantector.ui.plugins.base.BaseDependencyPlugin;

public abstract class ErrorPlugin extends BaseDependencyPlugin<ContextProvider> {
	public ErrorPlugin(ContextProvider dependency) {
		super(dependency);
	}

	public abstract void showUiError(UserError userError);

	public abstract void showUiError(UserError userError, @Nullable ErrorHideListener errorHideListener);

	public abstract void hideUiError();

	public interface ErrorHideListener {
		void hideError();
	}
}
