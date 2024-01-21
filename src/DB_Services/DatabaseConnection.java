package DB_Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/apartment_management_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "xa45jlSO";

    // Singleton instance
    private static DatabaseConnection instance;

    // Connection object
    private static Connection connection;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
    }

    // Get the singleton instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Get the database connection
    public static Connection getConnection() {
        return connection;
    }

    // Close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
    }
}

