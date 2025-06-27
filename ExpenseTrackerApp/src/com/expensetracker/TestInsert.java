package com.expensetracker;

public class TestInsert {
    public static void main(String[] args) {
        Expense exp = new Expense("Internet Bill", 1200.00, "Utilities", "2025-06-22");
        ExpenseDAO dao = new ExpenseDAO();

        boolean success = dao.addExpense(exp);
        if (success) {
            System.out.println("Expense added successfully!");
        } else {
            System.out.println("Failed to add expense.");
        }
    }
}
