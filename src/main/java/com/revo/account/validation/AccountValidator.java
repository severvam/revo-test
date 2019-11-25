package com.revo.account.validation;

import com.revo.account.AccountDto;
import com.revo.core.validation.Validator;

import javax.inject.Inject;
import java.util.Set;

public class AccountValidator extends Validator<AccountValidationRule, AccountDto> {

	@Inject
	public AccountValidator(Set<AccountValidationRule> validationRules) {
		super(validationRules);
	}

}
