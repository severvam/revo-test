package com.revo.core.validation;

public interface ValidationRule<T> {

	ValidationResult apply(T parameter);

}
