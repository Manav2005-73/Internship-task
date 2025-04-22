import java.io.*;
import java.util.*;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String title;
    String author;
    int quantity;

    public Book(int id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Quantity: " + quantity;
    }
}

class Library {
    private List<Book> books;
    private static final String FILE_NAME = "books.txt";

    public Library() {
        books = new ArrayList<>();
        loadBooks();
    }

    @SuppressWarnings("unchecked")
    private void loadBooks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            books = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (List<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            books = new ArrayList<>();
        }
    }

    void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
        System.out.println("Book added successfully!");
    }

    public void removeBook(int bookId) {
        boolean removed = books.removeIf(book -> book.id == bookId);
        if (removed) {
            saveBooks();
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.id == bookId) {
                return book;
            }
        }
        return null;
    }
}

class Student {
    private Library library;

    public Student(Library library) {
        this.library = library;
    }

    public void borrowBook(int bookId) {
        Book book = library.getBookById(bookId);
        if (book != null && book.quantity > 0) {
            book.quantity--;
            library.saveBooks();
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Book not available!");
        }
    }

    public void returnBook(int bookId) {
        Book book = library.getBookById(bookId);
        if (book != null) {
            book.quantity++;
            library.saveBooks();
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Invalid book ID!");
        }
    }

    public void viewBooks() {
        library.displayBooks();
    }
}

class Admin {
    private Library library;

    public Admin(Library library) {
        this.library = library;
    }

    public void addBook(int id, String title, String author, int quantity) {
        library.addBook(new Book(id, title, author, quantity));
    }

    public void removeBook(int bookId) {
        library.removeBook(bookId);
    }

    public void viewBooks() {
        library.displayBooks();
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin(library);
        Student student = new Student(library);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                scanner.next();
            }
            int choice = scanner.nextInt();

            if (choice == 1) {
                while (true) {
                    System.out.println("\nAdmin Menu:");
                    System.out.println("1. Add Book");
                    System.out.println("2. Remove Book");
                    System.out.println("3. View Books");
                    System.out.println("4. Logout");
                    System.out.print("Enter choice: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input! Enter a number.");
                        scanner.next();
                    }
                    int adminChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (adminChoice == 1) {
                        System.out.print("Enter Book ID: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Enter a number.");
                            scanner.next();
                        }
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Quantity: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Enter a number.");
                            scanner.next();
                        }
                        int quantity = scanner.nextInt();
                        admin.addBook(id, title, author, quantity);
                    } else if (adminChoice == 2) {
                        System.out.print("Enter Book ID to remove: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Enter a number.");
                            scanner.next();
                        }
                        int bookId = scanner.nextInt();
                        admin.removeBook(bookId);
                    } else if (adminChoice == 3) {
                        admin.viewBooks();
                    } else if (adminChoice == 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice! Try again.");
                    }
                }
            } else if (choice == 2) {
                while (true) {
                    System.out.println("\nStudent Menu:");
                    System.out.println("1. View Books");
                    System.out.println("2. Borrow Book");
                    System.out.println("3. Return Book");
                    System.out.println("4. Logout");
                    System.out.print("Enter choice: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input! Enter a number.");
                        scanner.next();
                    }
                    int studentChoice = scanner.nextInt();

                    if (studentChoice == 1) {
                        student.viewBooks();
                    } else if (studentChoice == 2) {
                        System.out.print("Enter Book ID to borrow: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Enter a number.");
                            scanner.next();
                        }
                        int bookId = scanner.nextInt();
                        student.borrowBook(bookId);
                    } else if (studentChoice == 3) {
                        System.out.print("Enter Book ID to return: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Enter a number.");
                            scanner.next();
                        }
                        int bookId = scanner.nextInt();
                        student.returnBook(bookId);
                    } else if (studentChoice == 4) {
                        break;
                    } else {
                        System.out.println("Invalid choice! Try again.");
                    }
                }
            } else if (choice == 3) {
                System.out.println("Exiting Library Management System...");
                break;
            } else {
                System.out.println("Invalid choice! Try again.");
            }
        }
        scanner.close();
    }
} 