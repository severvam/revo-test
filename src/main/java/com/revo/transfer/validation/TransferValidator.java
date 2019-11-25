package com.revo.transfer.validation;

import com.revo.core.validation.Validator;
import com.revo.transfer.TransferDto;

import javax.inject.Inject;
import java.util.Set;

public class TransferValidator extends Validator<TransferValidationRule, TransferDto> {

	@Inject
	public TransferValidator(Set<TransferValidationRule> validationRules) {
		super(validationRules);
	}

}
