import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nProduct Management System");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    productDAO.addProduct(new Product(name, description, price, quantity));
                    break;

                case 2:
                    List<Product> products = productDAO.getAllProducts();
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case 3:
                    System.out.print("Enter product ID to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    productDAO.updateProduct(new Product(id, newName, newDescription, newPrice, newQuantity));
                    break;

                case 4:
                    System.out.print("Enter product ID to delete: ");
                    int deleteId = scanner.nextInt();
                    productDAO.deleteProduct(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
