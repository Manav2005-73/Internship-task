import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void addProduct(Product product) {
        String sql = "INSERT INTO products (name, description, price, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());

            stmt.executeUpdate();
            System.out.println("Product added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DatabaseUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, quantity = ? WHERE id = ?";

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
