package DB_Services;

import Models.Apartment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDAO {

    private Connection connection;

    public ApartmentDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void createApartmentsTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS apartments (" +
                    "apartmentId INT AUTO_INCREMENT PRIMARY KEY," +
                    "address VARCHAR(255) NOT NULL," +
                    "numberOfRooms INT NOT NULL," +
                    "rentAmount DOUBLE NOT NULL)";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
    }

    // Create a new apartment
    public boolean createApartment(Apartment apartment) {
        try {
            String sql = "INSERT INTO apartments (address, numberOfRooms, rentAmount) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, apartment.getAddress());
                preparedStatement.setInt(2, apartment.getNumberOfRooms());
                preparedStatement.setDouble(3, apartment.getRentAmount());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the auto-generated key (apartmentId)
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedApartmentId = generatedKeys.getInt(1);
                        apartment.setApartmentId(generatedApartmentId);
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

    // Retrieve all apartments
    public List<Apartment> getAllApartments() {
        List<Apartment> apartments = new ArrayList<>();
        try {
            String sql = "SELECT * FROM apartments";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int apartmentId = resultSet.getInt("apartmentId");
                    String address = resultSet.getString("address");
                    int numberOfRooms = resultSet.getInt("numberOfRooms");
                    double rentAmount = resultSet.getDouble("rentAmount");

                    Apartment apartment = new Apartment(apartmentId, address, numberOfRooms, rentAmount);
                    apartments.add(apartment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return apartments;
    }

    // Update an existing apartment
    public boolean updateApartment(Apartment apartment) {
        try {
            String sql = "UPDATE apartments SET address=?, numberOfRooms=?, rentAmount=? WHERE apartmentId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, apartment.getAddress());
                preparedStatement.setInt(2, apartment.getNumberOfRooms());
                preparedStatement.setDouble(3, apartment.getRentAmount());
                preparedStatement.setInt(4, apartment.getApartmentId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return false;
    }

    // Delete an apartment by its ID
    public boolean deleteApartment(int apartmentId) {
        try {
            String sql = "DELETE FROM apartments WHERE apartmentId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, apartmentId);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return false;
    }

    // Search apartments based on filters
    public List<Apartment> searchApartments(String addressFilter, Integer numberOfRoomsFilter, Double rentAmountFilter) {
        List<Apartment> apartments = new ArrayList<>();
        try {
            // Build the SQL query dynamically based on provided filters
            StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM apartments WHERE 1=1");

            if (addressFilter != null && !addressFilter.isEmpty()) {
                sqlBuilder.append(" AND address LIKE ?");
            }

            if (numberOfRoomsFilter != null) {
                sqlBuilder.append(" AND numberOfRooms = ?");
            }

            if (rentAmountFilter != null) {
                sqlBuilder.append(" AND rentAmount = ?");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString())) {
                int parameterIndex = 1;

                if (addressFilter != null && !addressFilter.isEmpty()) {
                    preparedStatement.setString(parameterIndex++, "%" + addressFilter + "%");
                }

                if (numberOfRoomsFilter != null) {
                    preparedStatement.setInt(parameterIndex++, numberOfRoomsFilter);
                }

                if (rentAmountFilter != null) {
                    preparedStatement.setDouble(parameterIndex++, rentAmountFilter);
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int apartmentId = resultSet.getInt("apartmentId");
                        String address = resultSet.getString("address");
                        int numberOfRooms = resultSet.getInt("numberOfRooms");
                        double rentAmount = resultSet.getDouble("rentAmount");

                        Apartment apartment = new Apartment(apartmentId, address, numberOfRooms, rentAmount);
                        apartments.add(apartment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return apartments;
    }
}
