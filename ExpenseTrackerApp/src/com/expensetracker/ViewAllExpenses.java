package com.expensetracker;

import java.util.List;

public class ViewAllExpenses {
	public static void main(String[] args) {
		ExpenseDAO dao = new ExpenseDAO();
		List<Expense> expenses = dao.getAllExpenses();

		if (expenses.isEmpty()) {
			System.out.println("No expenses found.");
		} else {
			for (Expense exp : expenses) {
				System.out.println("----------------------------------");
				System.out.println("Title   : " + exp.getTitle());
				System.out.println("Amount  : ₹" + exp.getAmount());
				System.out.println("Category: " + exp.getCategory());
				System.out.println("Date    : " + exp.getExpenseDate());
			}
		}
	}
}
