package com.revo.core.persistence;

import com.dieselpoint.norm.Database;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Named;

public final class DatabaseConfig {

	private static final String DATABASE_URL = "database.url";
	private static final String DATABASE_USER = "database.user";

	private final Database db;

	@Getter
	private final String url;
	@Getter
	private final String user;

	@Inject
	public DatabaseConfig(@Named(DATABASE_URL) String url, @Named(DATABASE_USER) String user) {
		db = new Database();
		db.setJdbcUrl(url);
		db.setUser(user);
		this.url = url;
		this.user = user;
	}

	public Database db() {
		return db;
	}

}
