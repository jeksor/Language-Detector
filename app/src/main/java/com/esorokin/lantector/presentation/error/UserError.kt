package com.esorokin.lantector.presentation.error

/**
 * Contain localized data for directly display to user.
 * Must be clear from errorCode and other domain layer data!
 * Only title and message.
 */
class UserError(val title: String, val message: String)
