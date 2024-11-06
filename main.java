import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        addressBook.loadFromFile();
        addressBook.displayEntries();

        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        addressBook.searchByName(name);

        System.out.print("Do you want to add more entries? (y/n): ");
        String choice = scanner.nextLine();

        while (choice.equalsIgnoreCase("y")) {
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

            System.out.print("Do you want to add another entry? (y/n): ");
            choice = scanner.nextLine();
        }

        addressBook.saveToFile();
        scanner.close();
    }
}
