package com.esorokin.lantector.ui.plugins;

import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.esorokin.lantector.R;
import com.esorokin.lantector.ui.plugins.base.BaseDependencyPlugin;

public class AlertMessagePlugin extends BaseDependencyPlugin<ContextProvider> {
	private AlertDialog dialog;

	public AlertMessagePlugin(ContextProvider dependency) {
		super(dependency);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hideMessage();
	}

	public void showMessage(String title, MessageHideListener hideListener) {
		showMessage(title, "", getDependency().getContext().getString(R.string.ok), hideListener);
	}

	public void showMessage(String title, String message, String okText, MessageHideListener hideListener) {
		hideMessage();

		AlertDialog.Builder builder = new AlertDialog.Builder(getDependency().getContext());
		if (!TextUtils.isEmpty(title)) {
			builder.setTitle(title);
		}

		if (!TextUtils.isEmpty(message)) {
			builder.setMessage(message);
		}

		if (!TextUtils.isEmpty(okText)) {
			builder.setPositiveButton(okText, (dialogInterface, i) -> {
				if (hideListener != null) {
					hideListener.hideMessage();
				}
			});
		}

		dialog = builder.show();
	}

	public void hideMessage() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	public interface MessageHideListener {
		void hideMessage();
	}
}