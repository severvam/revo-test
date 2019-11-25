package com.revo.core.validation;

import lombok.Value;

@Value
public class ValidationResult {

	public static final ValidationResult OK = new ValidationResult(true, "");

	private final boolean valid;
	private final String message;

	public static ValidationResult fail(String message) {
		return new ValidationResult(false, message);
	}
}
