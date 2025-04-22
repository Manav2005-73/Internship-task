import java.io.IOException;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SocketException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = ((HttpServletRequest) request).getParameter("message");

        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            ((Object) response.getWriter()).println("Please fill out all required fields.");
            return;
        }

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO ContactUs (name, email, subject, message) VALUES (?, ?, ?, ?)")) {
            
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, subject);
            stmt.setString(4, message);
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ((Object) response).getWriter().println("Thank you! Your message has been submitted.");
            } else {
                response.getWriter().println("Error submitting your message.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ((Object) response).getWriter().println("Database error occurred!");
        }
    }
}
