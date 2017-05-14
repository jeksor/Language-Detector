package com.esorokin.lantector.model.network.api.handler;

public interface NetworkErrorAdapter {
	Throwable adaptNetworkError(Throwable throwable);
}
