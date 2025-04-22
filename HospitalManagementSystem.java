import java.util.*;
class Person {
    String name;
    int age;
    String gender;
    String contact;
    public Person(String name, int age, String gender, String contact) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Contact: " + contact;
    }
}
class Patient extends Person {
    String medicalHistory;
    String admissionDate;
    boolean isDischarged;
    public Patient(String name, int age, String gender, String contact, String medicalHistory, String admissionDate) {
        super(name, age, gender, contact);
        this.medicalHistory = medicalHistory;
        this.admissionDate = admissionDate;
        this.isDischarged = false;
    }
    @Override
    public String toString() {
        return super.toString() +
                ", Medical History: " + medicalHistory +
                ", Admission Date: " + admissionDate +
                ", Discharged: " + (isDischarged ? "Yes" : "No");
    }
}
class Staff extends Person {
    String role;
    String department;
    public Staff(String name, int age, String gender, String contact, String role, String department) {
        super(name, age, gender, contact);
        this.role = role;
        this.department = department;
    }
    @Override
    public String toString() {
        return super.toString() + ", Role: " + role + ", Department: " + department;
    }
}
class Bill {
    String patientName;
    double consultationFees;
    double roomCharges;
    double otherCharges;
    double totalAmount;
    public Bill(String patientName, double consultationFees, double roomCharges, double otherCharges) {
        this.patientName = patientName;
        this.consultationFees = consultationFees;
        this.roomCharges = roomCharges;
        this.otherCharges = otherCharges;
        this.totalAmount = consultationFees + roomCharges + otherCharges;
    }
    @Override
    public String toString() {
        return "Patient: " + patientName +
                ", Consultation Fees: " + consultationFees +
                ", Room Charges: " + roomCharges +
                ", Other Charges: " + otherCharges +
                ", Total Amount: " + totalAmount;
    }
}
public class HospitalManagementSystem {
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Staff> staffList = new ArrayList<>();
    static ArrayList<Bill> bills = new ArrayList<>();
    public static void addPatient(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();
        System.out.print("Enter Medical History: ");
        String history = sc.nextLine();
        System.out.print("Enter Admission Date: ");
        String admissionDate = sc.nextLine();
        patients.add(new Patient(name, age, gender, contact, history, admissionDate));
        System.out.println("Patient added successfully!");
    }
    public static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient p : patients) {
                System.out.println(p);
            }
        }
    }
    public static void updatePatient(Scanner sc) {
        System.out.print("Enter Patient Name to Update: ");
        String name = sc.nextLine();
        for (Patient p : patients) {
            if (p.name.equalsIgnoreCase(name)) {
                System.out.print("Enter New Contact: ");
                p.contact = sc.nextLine();
                System.out.print("Enter New Medical History: ");
                p.medicalHistory = sc.nextLine();
                System.out.println("Patient details updated successfully!");
                return;
            }
        }
        System.out.println("Patient not found.");
    }
    public static void addStaff(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();
        System.out.print("Enter Role: ");
        String role = sc.nextLine();
        System.out.print("Enter Department: ");
        String department = sc.nextLine();
        staffList.add(new Staff(name, age, gender, contact, role, department));
        System.out.println("Staff member added successfully!");
    }
    public static void viewStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            for (Staff s : staffList) {
                System.out.println(s);
            }
        }
    }
    public static void updateStaff(Scanner sc) {
        System.out.print("Enter Staff Name to Update: ");
        String name = sc.nextLine();
        for (Staff s : staffList) {
            if (s.name.equalsIgnoreCase(name)) {
                System.out.print("Enter New Contact: ");
                s.contact = sc.nextLine();
                System.out.print("Enter New Role: ");
                s.role = sc.nextLine();
                System.out.println("Staff details updated successfully!");
                return;
            }
        }
        System.out.println("Staff member not found.");
    }
    public static void generateBill(Scanner sc) {
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Consultation Fees: ");
        double consultationFees = sc.nextDouble();
        System.out.print("Enter Room Charges: ");
        double roomCharges = sc.nextDouble();
        System.out.print("Enter Other Charges: ");
        double otherCharges = sc.nextDouble();
        sc.nextLine();
        bills.add(new Bill(name, consultationFees, roomCharges, otherCharges));
        System.out.println("Bill generated successfully!");
    }
    public static void viewBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills found.");
        } else {
            for (Bill b : bills) {
                System.out.println(b);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Update Patient Details");
            System.out.println("4. Add Staff");
            System.out.println("5. View Staff");
            System.out.println("6. Update Staff Details");
            System.out.println("7. Generate Bill");
            System.out.println("8. View Bills");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1 -> addPatient(sc);
                case 2 -> viewPatients();
                case 3 -> updatePatient(sc);
                case 4 -> addStaff(sc);
                case 5 -> viewStaff();
                case 6 -> updateStaff(sc);
                case 7 -> generateBill(sc);
                case 8 -> viewBills();
                case 9 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
 