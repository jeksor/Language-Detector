package com.esorokin.lantector.ui.plugins;

import android.app.ProgressDialog;

import com.esorokin.lantector.R;
import com.esorokin.lantector.ui.plugins.base.BaseDependencyPlugin;

public class ProgressPlugin extends BaseDependencyPlugin<ContextProvider> {
	private ProgressDialog dialog;

	public ProgressPlugin(ContextProvider delegate) {
		super(delegate);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hideProgress();
	}

	public void showProgress() {
		hideProgress();
		dialog = new ProgressDialog(getDependency().getContext());
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setMessage(getDependency().getContext().getString(R.string.loading));
		dialog.show();
	}

	public void hideProgress() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}
}
