package com.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

import com.database.DatabaseUtility;
import com.database.QueryTask;

public class SendInputQueryTask extends QueryTask {

	@Override
	public Optional<QueryTask> submitTask() {
		Scanner scannerInput = new Scanner(System.in);
	    System.out.println("Enter username");
	    String input = scannerInput.nextLine();
	    
		try (Connection connection = DatabaseUtility.getConnection()) {
			connection.setAutoCommit(false);
				try (PreparedStatement saveStatement = connection.prepareStatement(
						"INSERT INTO info (id, input) VALUES (?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS)) {
					saveStatement.setInt(1, 0); // must be a constant as it auto increments for sql pool
					saveStatement.setString(2, input);
					saveStatement.execute();
				}
				connection.commit();
				connection.setAutoCommit(true);
				scannerInput.close();
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}