package com.revo.transfer.validation.rule;

import com.revo.account.persistence.Account;
import com.revo.account.persistence.AccountDao;
import com.revo.core.validation.ValidationResult;
import com.revo.transfer.TransferDto;
import com.revo.transfer.validation.TransferStatus;
import com.revo.transfer.validation.TransferValidationRule;

import javax.inject.Inject;
import java.util.Optional;

public class AccountExistsTransferValidationRule implements TransferValidationRule {

	private final AccountDao accountDao;

	@Inject
	public AccountExistsTransferValidationRule(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public ValidationResult apply(TransferDto parameter) {
		final Optional<Account> accountFrom = Optional.ofNullable(accountDao.findByAccountNumber(parameter.getAccountFrom()));
		final Optional<Account> accountTo = Optional.ofNullable(accountDao.findByAccountNumber(parameter.getAccountTo()));
		if (accountFrom.isPresent() && accountTo.isPresent()) {
			return ValidationResult.OK;
		}
		return ValidationResult.fail(TransferStatus.NOT_EXISTING_ACCOUNT.toString());
	}

}
