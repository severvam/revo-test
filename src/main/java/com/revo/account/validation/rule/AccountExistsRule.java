package com.revo.account.validation.rule;

import com.revo.account.AccountDto;
import com.revo.account.persistence.Account;
import com.revo.account.persistence.AccountDao;
import com.revo.account.validation.AccountStatus;
import com.revo.account.validation.AccountValidationRule;
import com.revo.core.validation.ValidationResult;

import javax.inject.Inject;
import java.util.Optional;

public class AccountExistsRule implements AccountValidationRule {

	private final AccountDao accountDao;

	@Inject
	public AccountExistsRule(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public ValidationResult apply(AccountDto parameter) {
		final Optional<Account> account = Optional.ofNullable(accountDao.findByAccountNumber(parameter.getNumber()));
		if (account.isPresent()) {
			return ValidationResult.fail(AccountStatus.ACCOUNT_ALREADY_EXISTS.toString());
		}
		return ValidationResult.OK;
	}

}
