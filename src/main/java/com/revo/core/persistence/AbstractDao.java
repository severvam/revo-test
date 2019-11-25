package com.revo.core.persistence;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.DbException;
import com.dieselpoint.norm.Transaction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractDao<T extends Persistable<I>, I> {

	private final DatabaseConfig databaseConfig;

	public AbstractDao(DatabaseConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}

	public void persist(T entity) {
		try {
			database().insert(entity);
		} catch (DbException e) {
			log.error("Error has occurred for entity save", e);
		}
	}

	public void persist(T entity, Transaction transaction) {
		try {
			database().transaction(transaction).insert(entity);
		} catch (DbException e) {
			log.error("Error has occurred for entity save", e);
		}
	}

	public void update(T entity, Transaction transaction) {
		try {
			database().transaction(transaction).update(entity);
		} catch (DbException e) {
			log.error("Can't update " + entity.toString(), e);
		}
	}

	public Transaction startTransaction() {
		return database().startTransaction();
	}

	protected Database database() {
		return databaseConfig.db();
	}

	public abstract Class<T> getEntityClass();

}
