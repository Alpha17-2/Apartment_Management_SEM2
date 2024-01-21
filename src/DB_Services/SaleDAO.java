package DB_Services;



import Models.Sale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {

    private Connection connection;

    public SaleDAO() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void createSalesTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS sales (" +
                    "saleId INT AUTO_INCREMENT PRIMARY KEY," +
                    "apartmentId INT NOT NULL," +
                    "saleDate DATE NOT NULL," +
                    "saleAmount DOUBLE NOT NULL," +
                    "FOREIGN KEY (apartmentId) REFERENCES apartments(apartmentId))";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
    }


    // Create a new sale
    public boolean createSale(Sale sale) {
        try {
            String sql = "INSERT INTO sales (apartmentId, salePrice, saleDate, buyerId) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, sale.getApartmentId());
                preparedStatement.setDouble(2, sale.getSalePrice());
                preparedStatement.setDate(3, new java.sql.Date(sale.getSaleDate().getTime()));
                preparedStatement.setInt(4, sale.getBuyerId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Retrieve the auto-generated key (saleId)
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedSaleId = generatedKeys.getInt(1);
                        sale.setSaleId(generatedSaleId);
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

    // Retrieve all sales
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sales";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int saleId = resultSet.getInt("saleId");
                    int apartmentId = resultSet.getInt("apartmentId");
                    double salePrice = resultSet.getDouble("salePrice");
                    java.util.Date saleDate = resultSet.getDate("saleDate");
                    int buyerId = resultSet.getInt("buyerId");

                    Sale sale = new Sale(saleId, apartmentId, salePrice, saleDate, buyerId);
                    sales.add(sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return sales;
    }

    // Update an existing sale
    public boolean updateSale(Sale sale) {
        try {
            String sql = "UPDATE sales SET apartmentId=?, salePrice=?, saleDate=?, buyerId=? WHERE saleId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, sale.getApartmentId());
                preparedStatement.setDouble(2, sale.getSalePrice());
                preparedStatement.setDate(3, new java.sql.Date(sale.getSaleDate().getTime()));
                preparedStatement.setInt(4, sale.getBuyerId());
                preparedStatement.setInt(5, sale.getSaleId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., log or throw a custom exception)
        }
        return false;
    }

    // Delete a sale by its ID
    public boolean deleteSale(int saleId) {
        try {
            String sql = "DELETE FROM sales WHERE saleId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, saleId);

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
