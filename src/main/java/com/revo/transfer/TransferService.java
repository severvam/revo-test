package com.revo.transfer;

import com.dieselpoint.norm.Transaction;
import com.revo.account.persistence.Account;
import com.revo.account.persistence.AccountDao;
import com.revo.core.AppDateTimeService;
import com.revo.transfer.persistence.Transfer;
import com.revo.transfer.persistence.TransferDao;

import javax.inject.Inject;

public class TransferService {

	private final TransferDao transferDao;
	private final AccountDao accountDao;
	private final AppDateTimeService appDateTimeService;

	@Inject
	public TransferService(TransferDao transferDao, AccountDao accountDao, AppDateTimeService appDateTimeService) {
		this.transferDao = transferDao;
		this.accountDao = accountDao;
		this.appDateTimeService = appDateTimeService;
	}

	public void transfer(TransferDto transferDto) {
		final Account accountFrom = accountDao.findByAccountNumber(transferDto.getAccountFrom());
		final Account accountTo = accountDao.findByAccountNumber(transferDto.getAccountTo());

		final var transfer = new Transfer(accountFrom.getId(), accountTo.getId(), appDateTimeService.currentDate(), transferDto.getAmount());

		final Transaction transaction = transferDao.startTransaction();
		accountFrom.setBalance(accountFrom.getBalance().subtract(transfer.getAmount()));
		accountTo.setBalance(accountTo.getBalance().add(transfer.getAmount()));
		transferDao.persist(transfer, transaction);
		accountDao.update(accountFrom, transaction);
		accountDao.update(accountTo, transaction);
		transaction.commit();
	}

}
