package com.revo.core.persistence;

import com.dieselpoint.norm.Database;
import com.dieselpoint.norm.DbException;
import com.dieselpoint.norm.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.generate;

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

	public Optional<T> find(T entity) {
		try {
			final var results = database().where("id = ?", entity.getId()).results(getEntityClass());
			return singleResult(results);
		} catch (DbException e) {
			log.error(format("Error has occurred for entity [%s]", entity.toString()), e);
		}
		return empty();
	}

	public Optional<T> find(I id) {
		try {
			final var results = database().where("id = ?", id).results(getEntityClass());
			return singleResult(results);
		} catch (DbException e) {
			log.error(format("Error has occurred for entitty search by id [%s] ", id), e);
		}
		return empty();
	}

	public void update(T entity) {
		try {
			database().update(entity);
		} catch (DbException e) {
			log.error("Can't update " + entity.toString(), e);
		}
	}

	public abstract String getTableName();

	public Transaction startTransaction() {
		return database().startTransaction();
	}

	protected Database database() {
		return databaseConfig.db();
	}

	protected String generatePlaceholders(Object[] ids) {
		final var placeholders = generate(() -> "?").limit(ids.length).collect(toList());
		return String.join(",", placeholders);
	}

	protected Optional<T> singleResult(List<T> results) {
		if (!results.isEmpty()) {
			return results.stream().findFirst();
		}
		return empty();
	}

	public abstract Class<T> getEntityClass();

}
