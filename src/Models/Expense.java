package Models;
import java.util.Date;

public class Expense {
    private int expenseId;
    private String description;
    private double amount;
    private Date expenseDate;

    // Constructors, getters, and setters

    public Expense(int expenseId, String description, double amount, Date expenseDate) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    // Additional methods if needed
}

