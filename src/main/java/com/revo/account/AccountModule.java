package com.revo.account;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.revo.account.persistence.AccountDao;
import com.revo.account.validation.AccountValidationRule;
import com.revo.account.validation.AccountValidator;
import com.revo.account.validation.rule.AccountExistsRule;
import com.revo.account.validation.rule.NegativeInitialBalanceRule;

public class AccountModule extends AbstractModule {

	@Override
	protected void configure() {
		super.configure();
		final Multibinder<AccountValidationRule> multibinder = Multibinder.newSetBinder(binder(), AccountValidationRule.class);

		bind(AccountResource.class).in(Singleton.class);
		bind(AccountDao.class).in(Singleton.class);
		bind(AccountValidator.class).in(Singleton.class);
		bind(AccountService.class).in(Singleton.class);
		multibinder.addBinding().to(AccountExistsRule.class);
		multibinder.addBinding().to(NegativeInitialBalanceRule.class);
	}

}
