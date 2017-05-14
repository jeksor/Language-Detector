package com.esorokin.lantector.ui.fragment.detect;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.esorokin.lantector.R;
import com.esorokin.lantector.databinding.FragmentDetectLanguageBinding;
import com.esorokin.lantector.presentation.error.UserError;
import com.esorokin.lantector.presentation.presenter.detect.DetectLanguagePresenter;
import com.esorokin.lantector.presentation.view.detect.DetectLanguageView;
import com.esorokin.lantector.ui.fragment.BaseFragment;
import com.esorokin.lantector.ui.plugins.AlertMessagePlugin;
import com.esorokin.lantector.ui.plugins.DialogErrorPlugin;
import com.esorokin.lantector.ui.plugins.ErrorPlugin;
import com.esorokin.lantector.ui.plugins.ProgressPlugin;
import com.esorokin.lantector.ui.plugins.ToolbarPlugin;
import com.esorokin.lantector.utils.ViewUtils;

public class DetectLanguageFragment extends BaseFragment implements DetectLanguageView {
	@InjectPresenter
	DetectLanguagePresenter presenter;

	private FragmentDetectLanguageBinding viewBinding;

	private ErrorPlugin errorPlugin;
	private ProgressPlugin progressPlugin;
	private AlertMessagePlugin alertMessagePlugin;

	@Override
	protected void initPlugins() {
		super.initPlugins();
		compositionPlugin().attach(new ToolbarPlugin((AppCompatActivity) getActivity()));
		compositionPlugin().attach(errorPlugin = new DialogErrorPlugin(this::getContext));
		compositionPlugin().attach(progressPlugin = new ProgressPlugin(this::getContext));
		compositionPlugin().attach(alertMessagePlugin = new AlertMessagePlugin(this::getContext));
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detect_language, container, false);
		return viewBinding.getRoot();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		viewBinding.detectLanguageButton.setOnClickListener(ignore -> {
			ViewUtils.hideKeyboard(getActivity());
			presenter.userClickStartDetect(viewBinding.inputEditText.getText().toString());
		});
	}

	@Override
	public void showDetectingProgress() {
		progressPlugin.showProgress();
	}

	@Override
	public void hideDetectingProgress() {
		progressPlugin.hideProgress();
	}

	@Override
	public void noInternetConnection() {
		Snackbar.make(viewBinding.detectLanguageButton, R.string.error_internet_connection, BaseTransientBottomBar.LENGTH_LONG)
				.setAction(R.string.button_retry, view -> presenter.userClickRetryDetect(viewBinding.inputEditText.getText().toString()))
				.show();
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
	public void showDetectingResult(String language) {
		alertMessagePlugin.showMessage(language.substring(0, 1).toUpperCase() + language.substring(1), () -> presenter.userHideDetectingResult());
	}

	@Override
	public void hideDetectingResult() {
		alertMessagePlugin.hideMessage();
		viewBinding.inputEditText.getText().clear();
	}
}
