import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        // Load address book from file
        addressBook.loadFromFile();

        // Main menu loop
        boolean running = true;
        while (running) {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Display all entries");
            System.out.println("2. Search by name");
            System.out.println("3. Add a new entry");
            System.out.println("4. Save address book to file");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Display all entries
                    addressBook.displayEntries();
                    break;
                case "2":
                    // Search by name
                    System.out.print("Enter the name to search: ");
                    String name = scanner.nextLine();
                    addressBook.searchByName(name);
                    break;
                case "3":
                    // Add a new entry
                    System.out.print("Enter Name: ");
                    String entryName = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    AddressBookEntry newEntry = new AddressBookEntry(entryName, phone, email, address);
                    addressBook.addEntry(newEntry);
                    System.out.println("New entry added.");
                    break;
                case "4":
                    // Save address book to file
                    addressBook.saveToFile();
                    break;
                case "5":
                    // Exit the program
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }

        // Close the scanner resource
        scanner.close();
    }
}
