package com.revo;

import com.revo.core.persistence.DatabaseConfig;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class H2Server {

	public void run(DatabaseConfig databaseConfig) {
		startH2(databaseConfig.getUrl(), databaseConfig.getUser());
		initDb(databaseConfig);
	}

	private void startH2(String url, String user) {
		try {
			org.h2.tools.Server.createTcpServer("-tcpAllowOthers", "-ifNotExists", "-webAllowOthers").start();
			Class.forName("org.h2.Driver");

			DriverManager.getConnection(url, user, "");
			org.h2.tools.Server.createWebServer().start();
		} catch (SQLException | ClassNotFoundException e) {
			log.error("Cannot start H2 [{1}]", e);
		}
	}

	private void initDb(DatabaseConfig config) {
		Connection connection = config.db().getConnection();
		try {
			final Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
			final var liquibase = new Liquibase("migration/main.xml", new ClassLoaderResourceAccessor(), database);
			liquibase.update("all");
		} catch (LiquibaseException e) {
			log.error("Database initialization error", e);
		}
	}

}
