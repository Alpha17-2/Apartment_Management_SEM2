package DB_Services;

// AccountDAO.java
import Models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

    private Connection connection; // Initialize this in the constructor

    // Constructor
    public AccountDAO() {
        DatabaseConnection.getInstance();
        this.connection = DatabaseConnection.getConnection();
        createAccountsTableIfNotExists();
    }

    // Method to create the accounts table if not already created
    public void createAccountsTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS accounts (" +
                "email VARCHAR(255) PRIMARY KEY," +
                "name VARCHAR(255)," +
                "password VARCHAR(255)," +
                "role VARCHAR(50)" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
    }

    // Method to find an account by email
    public Account getAccountByEmail(String email) {
        String sql = "SELECT * FROM accounts WHERE email=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAccount(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return null;
    }

    // Method to find an account by name
    public Account getAccountByName(String name) {
        String sql = "SELECT * FROM accounts WHERE name=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAccount(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return null;
    }

    // Method to create a new user account
    public boolean createNewUser(Account account) {
        if (getAccountByEmail(account.getEmail()) == null) {
            String sql = "INSERT INTO accounts (email, name, password, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, account.getEmail());
                statement.setString(2, account.getName());
                statement.setString(3, account.getPassword());
                statement.setString(4, account.getRole());
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exception (e.g., log or throw a custom exception)
                return false;
            }
        } else {
            // Account with the given email already exists
            return false;
        }
    }

    // Method to check login credentials using email and password
    public Account checkLoginCredentials(String email, String password, String role) {
        String sql = "SELECT * FROM accounts WHERE email=? AND password=? AND role=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, role);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAccount(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return null;
    }

    // Utility method to map ResultSet to Account object
    private Account mapResultSetToAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setEmail(resultSet.getString("email"));
        account.setName(resultSet.getString("name"));
        account.setPassword(resultSet.getString("password"));
        account.setRole(resultSet.getString("role"));
        // Set other fields as needed
        return account;
    }

    // Other methods for account CRUD operations
}
