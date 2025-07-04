package com.expensetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/expense_tracker";
	private static final String USER = "root"; // Replace with your MySQL username
	private static final String PASSWORD = "admin"; // Replace with your MySQL password

	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connected to the database successfully!");
			return conn;
		} catch (SQLException e) {
			System.out.println("Failed to connect to the database:");
			e.printStackTrace();
			return null;
		}
	}

	// For testing only
	public static void main(String[] args) {
		getConnection(); // Just tries to connect
	}
}
