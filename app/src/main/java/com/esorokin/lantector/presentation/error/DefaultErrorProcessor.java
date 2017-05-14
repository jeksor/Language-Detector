package com.esorokin.lantector.presentation.error;

import com.esorokin.lantector.R;
import com.esorokin.lantector.app.StringProvider;
import com.esorokin.lantector.model.network.exception.ApiException;
import com.esorokin.lantector.model.network.exception.AppException;
import com.esorokin.lantector.model.network.exception.UnhandledApiException;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class DefaultErrorProcessor implements ErrorProcessor {
	private final StringProvider stringProvider;

	@Inject
	public DefaultErrorProcessor(StringProvider stringProvider) {
		this.stringProvider = stringProvider;
	}

	@Override
	public UserError processError(Throwable throwable) {
		AppException appException;
		if (throwable instanceof AppException) {
			appException = (AppException) throwable;
		} else {
			appException = new UnhandledApiException("");
		}

		switch (appException.getAppErrorType()) {
			case NO_INTERNET:
				return new UserError(stringProvider.getString(R.string.error_default_title), stringProvider.getString(R.string.error_internet_connection));

			case REQUEST_TIMEOUT:
				return new UserError(stringProvider.getString(R.string.error_default_title), stringProvider.getString(R.string.error_timeout));

			case UNDEFINED_ERROR:
				return handleUnknownException(throwable);

			default:
				return handleApiException(appException);
		}
	}

	protected UserError handleApiException(AppException appException) {
		if (appException instanceof ApiException) {
			ApiException apiException = (ApiException) appException;
			switch (apiException.getAppErrorType()) {
				case NOT_ENOUGH_TEXT:
					return new UserError(stringProvider.getString(R.string.error_default_title), "Not enough text length!");

				default:
					return handleUnknownException(apiException);
			}
		} else {
			return handleUnknownException(appException);
		}
	}

	protected UserError handleUnknownException(Throwable unknown) {
		Timber.w("Unknown exception! Maybe something wrong with this app.");
		Timber.w(unknown);

		return new UserError(stringProvider.getString(R.string.error_unknown_title), stringProvider.getString(R.string.error_unknown_message));
	}
}
