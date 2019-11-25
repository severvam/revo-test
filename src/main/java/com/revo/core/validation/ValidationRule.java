package com.revo.core.validation;

public interface ValidationRule<T> {

	ValidationResult validate(T parameter);

}
