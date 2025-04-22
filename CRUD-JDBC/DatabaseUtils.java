import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/product_db";
    private static final String USER = "root"; // Change if needed
    private static final String PASSWORD = ""; // Set your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
