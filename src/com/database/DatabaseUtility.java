package com.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class DatabaseUtility {

	private final static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	private static boolean initiated = false;

	/**
	 * Sets up the Database, configure then checks for initiated
	 */
	public static void init() throws Exception {
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost/inputdb?useServerPrepStmts=false&rewriteBatchedStatements=true&autoReconnect=true");
		dataSource.setUser("root");
		dataSource.setPassword("");
		dataSource.setTestConnectionOnCheckout(true);
		dataSource.setTestConnectionOnCheckin(true);
		dataSource.setIdleConnectionTestPeriod(30);
		dataSource.setMaxStatementsPerConnection(50);
		dataSource.setMinPoolSize(3);
		dataSource.setMaxPoolSize(100);
		initiated = true;
	}

	/**
	 * Gets the current connection state for initiated
	 * @return dataSource.getConnection()
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		if (!initiated) {
			throw new RuntimeException("datasource has not been initiated");
		}
		return dataSource.getConnection();
	}

	/**
	 * Submits the task
	 * @param task
	 * @param input
	 * @return input
	 */
	public static Optional<QueryTask> sendTask(QueryTask task) {
		return task.submitTask();
	}
}