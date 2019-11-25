package com.revo.transfer.persistence;

import com.revo.core.persistence.AbstractDao;
import com.revo.core.persistence.DatabaseConfig;

import javax.inject.Inject;

public class TransferDao extends AbstractDao<Transfer, Long> {

	@Inject
	public TransferDao(DatabaseConfig databaseConfig) {
		super(databaseConfig);
	}

	@Override
	public Class<Transfer> getEntityClass() {
		return Transfer.class;
	}

}
