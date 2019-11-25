package com.revo.account.persistence;

import com.revo.core.persistence.AbstractDao;
import com.revo.core.persistence.DatabaseConfig;

import javax.inject.Inject;

public class AccountDao extends AbstractDao<Account, Long> {

	@Inject
	public AccountDao(DatabaseConfig databaseConfig) {
		super(databaseConfig);
	}

	public Account findByAccountNumber(String accountNumber) {
		return database().where("account_number = ?", accountNumber).first(getEntityClass());
	}

	@Override
	public Class<Account> getEntityClass() {
		return Account.class;
	}

}
