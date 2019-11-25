package com.revo.transfer.validation.rule;

import com.revo.account.persistence.Account;
import com.revo.account.persistence.AccountDao;
import com.revo.core.validation.ValidationResult;
import com.revo.transfer.TransferDto;
import com.revo.transfer.validation.TransferStatus;
import com.revo.transfer.validation.TransferValidationRule;

import javax.inject.Inject;

public class EnoughFundingValidationRule implements TransferValidationRule {

	private final AccountDao accountDao;

	@Inject
	public EnoughFundingValidationRule(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public ValidationResult apply(TransferDto parameter) {
		final Account account = accountDao.findByAccountNumber(parameter.getAccountFrom());
		if (account.getBalance().compareTo(parameter.getAmount()) < 0) {
			return ValidationResult.fail(TransferStatus.ERROR_INSUFFICIENT_FUNDS.toString());
		}
		return ValidationResult.OK;
	}
}
