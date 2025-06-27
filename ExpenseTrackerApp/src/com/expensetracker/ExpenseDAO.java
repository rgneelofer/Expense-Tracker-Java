package com.expensetracker;

import java.sql.*;
import java.util.*;

public class ExpenseDAO {

	public boolean addExpense(Expense expense) {
		String query = "INSERT INTO expenses (title, amount, category, expense_date, notes) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, expense.getTitle());
			stmt.setDouble(2, expense.getAmount());
			stmt.setString(3, expense.getCategory());
			stmt.setString(4, expense.getExpenseDate());
			stmt.setString(5, expense.getNotes());


			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (SQLException e) {
			System.out.println("Error inserting expense:");
			e.printStackTrace();
			return false;
		}
	}

	public List<Expense> getAllExpenses() {
		List<Expense> expenses = new ArrayList<>();

		String query = "SELECT * FROM expenses";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				String title = rs.getString("title");
				double amount = rs.getDouble("amount");
				String category = rs.getString("category");
				String date = rs.getString("expense_date");
				String notes = rs.getString("notes");
				Expense expense = new Expense(title, amount, category, date, notes);

				expense.setId(rs.getInt("id")); // Set the ID from DB
				expenses.add(expense);
			}

		} catch (SQLException e) {
			System.out.println("Error fetching expenses:");
			e.printStackTrace();
		}

		return expenses;
	}

//	public boolean deleteExpenseByTitle(String title) {
//	    String query = "DELETE FROM expenses WHERE title = ?";
//
//	    try (Connection conn = DBConnection.getConnection();
//	         PreparedStatement stmt = conn.prepareStatement(query)) {
//
//	        stmt.setString(1, title);
//
//	        int rowsDeleted = stmt.executeUpdate();
//	        return rowsDeleted > 0;
//
//	    } catch (SQLException e) {
//	        System.out.println("❌ Error deleting expense:");
//	        e.printStackTrace();
//	        return false;
//	    }
//	}

	public boolean deleteExpenseById(int id) {
		String query = "DELETE FROM expenses WHERE id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, id);

			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;

		} catch (SQLException e) {
			System.out.println("Error deleting expense by ID:");
			e.printStackTrace();
			return false;
		}
	}

	public double getTotalExpenses() {
		String query = "SELECT SUM(amount) AS total FROM expenses";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				return rs.getDouble("total");
			}

		} catch (SQLException e) {
			System.out.println("Error calculating total expenses:");
			e.printStackTrace();
		}

		return 0.0;
	}
	
	public List<Expense> getExpensesByCategory(String category) {
	    List<Expense> expenses = new ArrayList<>();
	    String query = "SELECT * FROM expenses WHERE category = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, category);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String title = rs.getString("title");
	            double amount = rs.getDouble("amount");
	            String date = rs.getString("expense_date");
	            String notes = rs.getString("notes");
	            Expense expense = new Expense(title, amount, category, date, notes);

	            expense.setId(rs.getInt("id"));  // set ID as well
	            expenses.add(expense);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error fetching by category:");
	        e.printStackTrace();
	    }

	    return expenses;
	}

	public List<Expense> getExpensesByDate(String date) {
	    List<Expense> expenses = new ArrayList<>();
	    String query = "SELECT * FROM expenses WHERE expense_date = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, date);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String title = rs.getString("title");
	            double amount = rs.getDouble("amount");
	            String category = rs.getString("category");
	            String notes = rs.getString("notes");
	            Expense expense = new Expense(title, amount, category, date, notes);

	            expense.setId(rs.getInt("id")); // get the id
	            expenses.add(expense);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error fetching expenses by date:");
	        e.printStackTrace();
	    }

	    return expenses;
	}


}
