import java.io.*;
import java.util.*;

public class StudentInformationSystem {
    private static final String FILE_NAME = "student_data.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student\n2. Delete Student\n3. Display Student Information\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    deleteStudent(scanner);
                    break;
                case 3:
                    displayStudent(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter Roll Number: ");
            String rollNumber = scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Division: ");
            String division = scanner.nextLine();

            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            writer.println(rollNumber + "," + name + "," + division + "," + address);
            System.out.println("Student added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private static void deleteStudent(Scanner scanner) {
        try {
            File inputFile = new File(FILE_NAME);
            File tempFile = new File("temp_student_data.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            System.out.print("Enter Roll Number to delete: ");
            String rollNumberToDelete = scanner.nextLine();
            String currentLine;

            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith(rollNumberToDelete + ",")) {
                    found = true;
                    continue;
                }
                writer.println(currentLine);
            }

            writer.close();
            reader.close();

            if (!found) {
                System.out.println("Student not found.");
                return;
            }

            if (!inputFile.delete()) {
                System.out.println("Error deleting student.");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Error updating student data.");
            } else {
                System.out.println("Student deleted successfully.");
            }

        } catch (IOException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    private static void displayStudent(Scanner scanner) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            System.out.print("Enter Roll Number to display information: ");
            String rollNumberToDisplay = scanner.nextLine();
            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(rollNumberToDisplay)) {
                    found = true;
                    System.out.println("Roll Number: " + data[0]);
                    System.out.println("Name: " + data[1]);
                    System.out.println("Division: " + data[2]);
                    System.out.println("Address: " + data[3]);
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found.");
            }

        } catch (IOException e) {
            System.out.println("Error displaying student information: " + e.getMessage());
        }
    }
}
