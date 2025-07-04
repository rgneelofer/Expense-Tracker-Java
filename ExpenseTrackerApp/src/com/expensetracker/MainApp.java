package com.expensetracker;

import java.util.*;

public class MainApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ExpenseDAO dao = new ExpenseDAO();

		while (true) {
			System.out.println("\n======== Expense Tracker Menu ========");

			System.out.println("1. Add Expense");
			System.out.println("2. View All Expenses");
			System.out.println("3. Delete Expense by ID");
			System.out.println("4. View Total Expenses");
			System.out.println("5. Search by Category");
			System.out.println("6. Search by Date");
			System.out.println("7. Exit");
			System.out.print("Choose an option (1-7): ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // consume leftover newline

			switch (choice) {
			case 1:
				// Add Expense
				System.out.print("Enter Title       : ");
				String title = scanner.nextLine();

				System.out.print("Enter Amount      : ₹");
				double amount = scanner.nextDouble();
				scanner.nextLine(); // consume newline

				System.out.print("Enter Category    : ");
				String category = scanner.nextLine();

				System.out.print("Enter Date (yyyy-MM-dd): ");
				String date = scanner.nextLine();
				
				System.out.print("Enter Notes (optional): ");
				String notes = scanner.nextLine();

				Expense expense = new Expense(title, amount, category, date, notes);

				boolean success = dao.addExpense(expense);

				if (success) {
					System.out.println("Expense added successfully!");
				} else {
					System.out.println("Failed to add expense.");
				}
				break;

			case 2:
				// View Expenses
				List<Expense> expenses = dao.getAllExpenses();

				if (expenses.isEmpty()) {
					System.out.println("No expenses found.");
				} else {
					System.out.println("\n-------- All Expenses --------");
					for (Expense exp : expenses) {
						System.out.println("ID      : " + exp.getId());
						System.out.println("Title   : " + exp.getTitle());
						System.out.println("Amount  : ₹" + exp.getAmount());
						System.out.println("Category: " + exp.getCategory());
						System.out.println("Date    : " + exp.getExpenseDate());
						System.out.println("Notes   : " + exp.getNotes());

						System.out.println("-----------------------------");
					}

				}
				break;

			case 3:
				//Delete Expense by ID
				System.out.print("Enter the ID of the expense to delete: ");
				int deleteId = scanner.nextInt();
				scanner.nextLine(); // consume newline

				boolean deletedById = dao.deleteExpenseById(deleteId);
				if (deletedById) {
					System.out.println("Expense deleted successfully.");
				} else {
					System.out.println("No expense found with that ID.");
				}
				break;

			case 4:
				//View Total Expenses
				double total = dao.getTotalExpenses();
				System.out.println("Total Expenses: ₹" + total);
				break;

			case 5:
				// Search by Category
				System.out.print("Enter category to search: ");
				String searchCategory = scanner.nextLine();
				List<Expense> categoryExpenses = dao.getExpensesByCategory(searchCategory);

				if (categoryExpenses.isEmpty()) {
					System.out.println("No expenses found in this category.");
				} else {
					for (Expense exp : categoryExpenses) {
					    System.out.println("ID      : " + exp.getId());
					    System.out.println("Title   : " + exp.getTitle());
					    System.out.println("Amount  : Rs. " + exp.getAmount());
					    System.out.println("Date    : " + exp.getExpenseDate());
					    System.out.println("Notes   : " + exp.getNotes()); 
					    System.out.println("-----------------------------");
					}

				}
				break;

			case 6:
				//Search by Date
				System.out.print("Enter date (yyyy-MM-dd): ");
				String searchDate = scanner.nextLine();
				List<Expense> dateExpenses = dao.getExpensesByDate(searchDate);

				if (dateExpenses.isEmpty()) {
					System.out.println("No expenses found on this date.");
				} else {
					for (Expense exp : dateExpenses) {
					    System.out.println("ID      : " + exp.getId());
					    System.out.println("Title   : " + exp.getTitle());
					    System.out.println("Amount  : ₹" + exp.getAmount());
					    System.out.println("Category: " + exp.getCategory());
					    System.out.println("Notes   : " + exp.getNotes());  
					    System.out.println("-----------------------------");
					}
				}
				break;

			case 7:
				//Exit
				System.out.println("Exiting... Goodbye!");
				System.exit(0);
				break;

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
