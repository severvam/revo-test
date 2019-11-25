package com.revo.core.validation;

import java.util.Set;

public class Validator<R extends ValidationRule<T>, T> {

	private final Set<R> validationRules;

	public Validator(Set<R> validationRules) {
		this.validationRules = validationRules;
	}

	public ValidationResult validate(T parameter) {
		return validationRules
				.stream()
				.map(rule -> rule.apply(parameter))
				.filter(result -> !result.isValid())
				.findFirst()
				.orElse(ValidationResult.OK);
	}

}
