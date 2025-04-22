import java.sql.*;
import java.util.Scanner;

public class LibrarySystem {
    private static final String URL = "jdbc:mysql://localhost:3306/LibraryDB";
    private static final String USER = "root";  // Change to your MySQL username
    private static final String PASSWORD = "";  // Change to your MySQL password

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Add a book
    public static void addBook(String title, String author, int quantity) {
        String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
            System.out.println("✅ Book added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding book: " + e.getMessage());
        }
    }

    // View all books
    public static void viewBooks() {
        String sql = "SELECT * FROM books";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n📚 Available Books:");
            boolean found = false;
            while (rs.next()) {
                System.out.println("📖 ID: " + rs.getInt("book_id") + " | Title: " + rs.getString("title") +
                        " | Author: " + rs.getString("author") + " | Quantity: " + rs.getInt("quantity"));
                found = true;
            }
            if (!found) {
                System.out.println("❌ No books found in the library.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching books: " + e.getMessage());
        }
    }

    // Register a member
    public static void registerMember(String firstName, String lastName, String email) {
        String sql = "INSERT INTO members (first_name, last_name, email) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("✅ Member registered successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error registering member: " + e.getMessage());
        }
    }

    // Borrow a book
    public static void borrowBook(int memberId, int bookId) {
        String sql = "INSERT INTO loans (book_id, member_id) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, memberId);
            pstmt.executeUpdate();
            System.out.println("✅ Book borrowed successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error borrowing book: " + e.getMessage());
        }
    }

    // Reserve a book
    public static void reserveBook(int memberId, int bookId) {
        String sql = "INSERT INTO reservations (book_id, member_id) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, memberId);
            pstmt.executeUpdate();
            System.out.println("✅ Book reserved successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error reserving book: " + e.getMessage());
        }
    }

    // View unpaid fines
    public static void viewUnpaidFines(int memberId) {
        String sql = "SELECT fine_amount FROM fines WHERE member_id = ? AND paid_status = 'unpaid'";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\n💰 Unpaid Fines:");
            boolean hasFines = false;
            while (rs.next()) {
                System.out.println("Unpaid Fine: $" + rs.getDouble("fine_amount"));
                hasFines = true;
            }
            if (!hasFines) {
                System.out.println("No unpaid fines found.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching fines: " + e.getMessage());
        }
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n📘 Library Management System");
            System.out.println("1️⃣ Add Book");
            System.out.println("2️⃣ View Books");
            System.out.println("3️⃣ Register Member");
            System.out.println("4️⃣ Borrow Book");
            System.out.println("5️⃣ Reserve Book");
            System.out.println("6️⃣ View Unpaid Fines");
            System.out.println("7️⃣ Exit");
            System.out.print("👉 Enter choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    addBook(title, author, quantity);
                    break;

                case 2:  // ✅ View Books Case Added
                    viewBooks();
                    break;

                case 3:
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    registerMember(firstName, lastName, email);
                    break;

                case 4:
                    System.out.print("Enter Member ID: ");
                    int memberId = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    borrowBook(memberId, bookId);
                    break;

                case 5:
                    System.out.print("Enter Member ID: ");
                    int memberIdReserve = scanner.nextInt();
                    System.out.print("Enter Book ID: ");
                    int bookIdReserve = scanner.nextInt();
                    reserveBook(memberIdReserve, bookIdReserve);
                    break;

                case 6:
                    System.out.print("Enter Member ID: ");
                    int memberIdFine = scanner.nextInt();
                    viewUnpaidFines(memberIdFine);
                    break;

                case 7:
                    System.out.println("🚀 Exiting Library System...");
                    running = false;
                    break;

                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        }

        scanner.close();
    }
}
