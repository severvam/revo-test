package com.revo.account.validation.rule;

import com.revo.account.AccountDto;
import com.revo.account.validation.AccountStatus;
import com.revo.account.validation.AccountValidationRule;
import com.revo.core.validation.ValidationResult;

import java.math.BigDecimal;

public class NegativeIntinalBalanceRule implements AccountValidationRule {

	@Override
	public ValidationResult apply(AccountDto parameter) {
		if (parameter.getBalance().compareTo(BigDecimal.ZERO) < 0) {
			return ValidationResult.fail(AccountStatus.NEGATIVE_INITIAL_AMOUNT.toString());
		}
		return ValidationResult.OK;
	}

}
