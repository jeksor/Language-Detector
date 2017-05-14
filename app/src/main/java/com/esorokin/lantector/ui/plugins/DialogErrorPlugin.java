package com.esorokin.lantector.ui.plugins;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.esorokin.lantector.R;
import com.esorokin.lantector.presentation.error.UserError;

public class DialogErrorPlugin extends ErrorPlugin {
	private AlertDialog dialog;

	public DialogErrorPlugin(ContextProvider delegate) {
		super(delegate);
	}

	@Override
	public void showUiError(UserError userError) {
		showUiError(userError, null);
	}

	@Override
	public void showUiError(UserError userError, @Nullable ErrorHideListener errorHideListener) {
		hideUiError();
		dialog = new AlertDialog.Builder(getDependency().getContext())
				.setTitle(userError.getTitle())
				.setMessage(userError.getMessage())
				.setCancelable(true)
				.setPositiveButton(R.string.ok, (dialogInterface, i) -> {
					if (errorHideListener != null) {
						errorHideListener.hideError();
					}
				})
				.show();
	}

	@Override
	public void hideUiError() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hideUiError();
	}
}
