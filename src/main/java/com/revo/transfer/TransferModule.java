package com.revo.transfer;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.revo.transfer.persistence.TransferDao;
import com.revo.transfer.validation.TransferValidator;
import com.revo.transfer.validation.rule.AccountExistsTransferValidationRule;
import com.revo.transfer.validation.rule.EnoughFundingValidationRule;
import com.revo.transfer.validation.rule.NegativeAmountRule;
import com.revo.transfer.validation.TransferValidationRule;
import com.revo.transfer.validation.rule.ZeroAmountRule;

public class TransferModule extends AbstractModule {

	@Override
	protected void configure() {
		super.configure();
		final Multibinder<TransferValidationRule> multibinder = Multibinder.newSetBinder(binder(), TransferValidationRule.class);

		bind(TransferResource.class).in(Singleton.class);
		bind(TransferDao.class).in(Singleton.class);
		bind(TransferService.class).in(Singleton.class);
		bind(TransferValidator.class).in(Singleton.class);
		multibinder.addBinding().to(EnoughFundingValidationRule.class);
		multibinder.addBinding().to(NegativeAmountRule.class);
		multibinder.addBinding().to(AccountExistsTransferValidationRule.class);
		multibinder.addBinding().to(ZeroAmountRule.class);
	}

}
