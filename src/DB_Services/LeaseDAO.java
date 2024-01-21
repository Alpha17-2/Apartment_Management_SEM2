package DB_Services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.Lease;

public class LeaseDAO {

    private Connection connection;

    public LeaseDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void createLeasesTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS leases (" +
                    "leaseId INT AUTO_INCREMENT PRIMARY KEY," +
                    "tenantId INT NOT NULL," +
                    "apartmentId INT NOT NULL," +
                    "startDate DATE NOT NULL," +
                    "endDate DATE NOT NULL," +
                    "FOREIGN KEY (tenantId) REFERENCES tenants(tenantId)," +
                    "FOREIGN KEY (apartmentId) REFERENCES apartments(apartmentId))";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
    }

    // Create a new lease
    public boolean createLease(Lease lease) {
        try {
            String sql = "INSERT INTO leases (apartmentId, tenantId, startDate, endDate, monthlyRent) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, lease.getApartmentId());
                preparedStatement.setInt(2, lease.getTenantId());
                preparedStatement.setDate(3, new java.sql.Date(lease.getStartDate().getTime()));
                preparedStatement.setDate(4, new java.sql.Date(lease.getEndDate().getTime()));
                preparedStatement.setDouble(5, lease.getMonthlyRent());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the auto-generated key (leaseId)
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedLeaseId = generatedKeys.getInt(1);
                        lease.setLeaseId(generatedLeaseId);
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

    // Retrieve all leases
    public List<Lease> getAllLeases() {
        List<Lease> leases = new ArrayList<>();
        try {
            String sql = "SELECT * FROM leases";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int leaseId = resultSet.getInt("leaseId");
                    int apartmentId = resultSet.getInt("apartmentId");
                    int tenantId = resultSet.getInt("tenantId");
                    java.util.Date startDate = resultSet.getDate("startDate");
                    java.util.Date endDate = resultSet.getDate("endDate");
                    double monthlyRent = resultSet.getDouble("monthlyRent");

                    Lease lease = new Lease(leaseId, apartmentId, tenantId, startDate, endDate, monthlyRent);
                    leases.add(lease);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return leases;
    }

    // Update an existing lease
    public boolean updateLease(Lease lease) {
        try {
            String sql = "UPDATE leases SET apartmentId=?, tenantId=?, startDate=?, endDate=?, monthlyRent=? WHERE leaseId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, lease.getApartmentId());
                preparedStatement.setInt(2, lease.getTenantId());
                preparedStatement.setDate(3, new java.sql.Date(lease.getStartDate().getTime()));
                preparedStatement.setDate(4, new java.sql.Date(lease.getEndDate().getTime()));
                preparedStatement.setDouble(5, lease.getMonthlyRent());
                preparedStatement.setInt(6, lease.getLeaseId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return false;
    }

    // Delete a lease by its ID
    public boolean deleteLease(int leaseId) {
        try {
            String sql = "DELETE FROM leases WHERE leaseId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, leaseId);

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
