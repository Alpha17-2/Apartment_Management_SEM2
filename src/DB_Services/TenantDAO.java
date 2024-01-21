package DB_Services;


import Models.Tenant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TenantDAO {

    private Connection connection;

    public TenantDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void createTenantsTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tenants (" +
                    "tenantId INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "contactNumber VARCHAR(20) NOT NULL," +
                    "email VARCHAR(255) NOT NULL)";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
    }
    // Create a new tenant
    public boolean createTenant(Tenant tenant) {
        try {
            String sql = "INSERT INTO tenants (name, contactNumber, email) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, tenant.getName());
                preparedStatement.setString(2, tenant.getContactNumber());
                preparedStatement.setString(3, tenant.getEmail());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the auto-generated key (tenantId)
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedTenantId = generatedKeys.getInt(1);
                        tenant.setTenantId(generatedTenantId);
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

    // Retrieve all tenants
    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tenants";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int tenantId = resultSet.getInt("tenantId");
                    String name = resultSet.getString("name");
                    String contactNumber = resultSet.getString("contactNumber");
                    String email = resultSet.getString("email");

                    Tenant tenant = new Tenant(tenantId, name, contactNumber, email);
                    tenants.add(tenant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return tenants;
    }

    // Update an existing tenant
    public boolean updateTenant(Tenant tenant) {
        try {
            String sql = "UPDATE tenants SET name=?, contactNumber=?, email=? WHERE tenantId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, tenant.getName());
                preparedStatement.setString(2, tenant.getContactNumber());
                preparedStatement.setString(3, tenant.getEmail());
                preparedStatement.setInt(4, tenant.getTenantId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return false;
    }

    // Delete a tenant by its ID
    public boolean deleteTenant(int tenantId) {
        try {
            String sql = "DELETE FROM tenants WHERE tenantId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tenantId);

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
