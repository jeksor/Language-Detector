package com.esorokin.lantector.presentation.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * <pre>
 * Contain localized data for directly display to user.
 * Must be clear from errorCode and other domain layer data!
 * Only title and message.
 * <pre/>
 */
@Data
@Getter
@AllArgsConstructor
public final class UserError {
	private String title;
	private String message;
}
