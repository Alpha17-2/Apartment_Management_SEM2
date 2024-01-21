package DB_Services;

import Models.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    private Connection connection;

    public ExpenseDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void createExpensesTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS expenses (" +
                    "expenseId INT AUTO_INCREMENT PRIMARY KEY," +
                    "description VARCHAR(255) NOT NULL," +
                    "amount DOUBLE NOT NULL," +
                    "expenseDate DATE NOT NULL)";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
    }

    // Create a new expense
    public boolean createExpense(Expense expense) {
        try {
            String sql = "INSERT INTO expenses (description, amount, expenseDate) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, expense.getDescription());
                preparedStatement.setDouble(2, expense.getAmount());
                preparedStatement.setDate(3, new java.sql.Date(expense.getExpenseDate().getTime()));

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the auto-generated key (expenseId)
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedExpenseId = generatedKeys.getInt(1);
                        expense.setExpenseId(generatedExpenseId);
                    }

                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return false;
    }

    // Retrieve all expenses
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM expenses";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int expenseId = resultSet.getInt("expenseId");
                    String description = resultSet.getString("description");
                    double amount = resultSet.getDouble("amount");
                    java.util.Date expenseDate = resultSet.getDate("expenseDate");

                    Expense expense = new Expense(expenseId, description, amount, expenseDate);
                    expenses.add(expense);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return expenses;
    }

    // Update an existing expense
    public boolean updateExpense(Expense expense) {
        try {
            String sql = "UPDATE expenses SET description=?, amount=?, expenseDate=? WHERE expenseId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, expense.getDescription());
                preparedStatement.setDouble(2, expense.getAmount());
                preparedStatement.setDate(3, new java.sql.Date(expense.getExpenseDate().getTime()));
                preparedStatement.setInt(4, expense.getExpenseId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return false;
    }

    // Delete an expense by its ID
    public boolean deleteExpense(int expenseId) {
        try {
            String sql = "DELETE FROM expenses WHERE expenseId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, expenseId);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return false;
    }
}
