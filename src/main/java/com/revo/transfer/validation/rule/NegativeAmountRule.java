package com.revo.transfer.validation.rule;

import com.revo.core.validation.ValidationResult;
import com.revo.transfer.TransferDto;
import com.revo.transfer.validation.TransferStatus;
import com.revo.transfer.validation.TransferValidationRule;

import java.math.BigDecimal;

public class NegativeAmountRule implements TransferValidationRule {

	@Override
	public ValidationResult apply(TransferDto parameter) {
		final boolean isNegativeAmount = parameter.getAmount().compareTo(BigDecimal.ZERO) < 0;
		if (isNegativeAmount) {
			return ValidationResult.fail(TransferStatus.ERROR_NEGATIVE_AMOUNT.toString());
		}
		return ValidationResult.OK;
	}

}
