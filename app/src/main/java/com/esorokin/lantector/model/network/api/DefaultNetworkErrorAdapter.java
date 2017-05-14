package com.esorokin.lantector.model.network.api;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import com.esorokin.lantector.model.network.api.handler.NetworkErrorAdapter;
import com.esorokin.lantector.model.network.exception.NoInternetException;
import com.esorokin.lantector.model.network.exception.TimeoutException;
import com.esorokin.lantector.model.network.exception.UnhandledApiException;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class DefaultNetworkErrorAdapter implements NetworkErrorAdapter {
	@Inject
	public DefaultNetworkErrorAdapter() {
		//inject
	}

	@Override
	public Throwable adaptNetworkError(Throwable throwable) {
		Timber.e(throwable);

		if (throwable instanceof SocketTimeoutException) {
			return new TimeoutException();

		} else if (throwable instanceof UnknownHostException) {
			return new NoInternetException();

		} else {
			return new UnhandledApiException(throwable.getLocalizedMessage());
		}
	}
}
