package com.esorokin.lantector.presentation.error;

/**
 * Using by presenters for localize domain exceptions into user friendly messages.
 */
public interface ErrorProcessor {
	UserError processError(Throwable throwable);
}
