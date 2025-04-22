import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {
    private static final Statement DatabaseConnector = null;

    public static void insertStudent(Student student) {
        String sql = "INSERT INTO students (name, age, email, course) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getCourse());
            pstmt.executeUpdate();
            System.out.println("Student registered successfully!");
        } catch (SQLException e) {
        }
    }
}