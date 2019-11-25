package com.revo.account;

import com.revo.account.persistence.Account;
import com.revo.account.persistence.AccountDao;
import com.revo.core.AppDateTimeService;

import javax.inject.Inject;

public class AccountService {

	private final AccountDao accountDao;
	private final AppDateTimeService appDateTimeService;

	@Inject
	public AccountService(AccountDao accountDao, AppDateTimeService appDateTimeService) {
		this.accountDao = accountDao;
		this.appDateTimeService = appDateTimeService;
	}

	void createAccount(AccountDto accountDto) {
		final Account account = new Account();
		account.setNumber(accountDto.getNumber());
		account.setBalance(accountDto.getBalance());
		account.setCreatedDate(appDateTimeService.currentDate());
		accountDao.persist(account);
	}

	Account findByAccountNumber(String number) {
		return accountDao.findByAccountNumber(number);
	}
}
