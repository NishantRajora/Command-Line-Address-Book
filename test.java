import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class representing an address book entry
class AddressBookEntry implements Serializable {
    private static final int NAME_SIZE = 50;
    private static final int PHONE_SIZE = 15;
    private static final int EMAIL_SIZE = 50;
    private static final int ADDRESS_SIZE = 100;
    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String email;
    private String address;

    // Constructor
    public AddressBookEntry(String name, String phone, String email, String address) {
        this.name = padOrTrim(name, NAME_SIZE);
        this.phone = padOrTrim(phone, PHONE_SIZE);
        this.email = padOrTrim(email, EMAIL_SIZE);
        this.address = padOrTrim(address, ADDRESS_SIZE);
    }

    // Utility function to pad or trim strings to a fixed size
    private static String padOrTrim(String value, int length) {
        if (value.length() > length) return value.substring(0, length);
        else return String.format("%-" + length + "s", value);
    }

    // Display the entry
    public void display() {
        System.out.printf("Name: %s, Phone: %s, Email: %s, Address: %s%n", name, phone, email, address);
    }

    // Getters for serialization and deserialization
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
}

// Class to manage the address book
public class test {
    private List<AddressBookEntry> entries;
    private static final String FILENAME = "address_book.dat";

    // Constructor
    public test() {
        entries = new ArrayList<>();
    }

    // Load address book entries from a binary file
    public void loadFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            entries = (List<AddressBookEntry>) inputStream.readObject();
            System.out.println("Address book loaded successfully with " + entries.size() + " entries.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing file found or file is empty. Starting a new address book.");
        }
    }

    // Save address book entries to a binary file
    public void saveToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            outputStream.writeObject(entries);
            System.out.println("Address book saved successfully.");
        } catch (IOException e) {
            System.err.println("Error: Could not save address book to file.");
            e.printStackTrace();
        }
    }

    // Add a new entry to the address book
    public void addEntry(AddressBookEntry entry) {
        entries.add(entry);
    }

    // Display all entries in the address book
    public void displayEntries() {
        if (entries.isEmpty()) {
            System.out.println("No entries found in the address book.");
        } else {
            entries.forEach(AddressBookEntry::display);
        }
    }

    // Main function
    public static void main(String[] args) {
        test addressBook = new test();
        Scanner scanner = new Scanner(System.in);

        // Load entries from file
        addressBook.loadFromFile();

        // Display loaded entries
        addressBook.displayEntries();

        // Optionally, add more entries
        System.out.print("Do you want to add more entries? (y/n): ");
        String choice = scanner.nextLine();

        while (choice.equalsIgnoreCase("y")) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Address: ");
            String address = scanner.nextLine();

            AddressBookEntry newEntry = new AddressBookEntry(name, phone, email, address);
            addressBook.addEntry(newEntry);

            System.out.print("Do you want to add another entry? (y/n): ");
            choice = scanner.nextLine();
        }

        // Save updated entries to file
        addressBook.saveToFile();

        scanner.close();
    }
}
