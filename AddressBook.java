import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<AddressBookEntry> entries;
    private BPlusTree<String, AddressBookEntry> nameIndex;
    private static final String FILENAME = "address_book.dat";

    public AddressBook() {
        entries = new ArrayList<>();
        nameIndex = new BPlusTree<>(4); // B+ tree order, can be adjusted as needed
    }

    public void loadFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            entries = (List<AddressBookEntry>) inputStream.readObject();
            for (AddressBookEntry entry : entries) {
                nameIndex.insert(entry.getName().trim(), entry);
            }
            System.out.println("Address book loaded with " + entries.size() + " entries.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing file found or file is empty. Starting a new address book.");
        }
    }

    public void addEntry(AddressBookEntry entry) {
        entries.add(entry);
        nameIndex.insert(entry.getName().trim(), entry);
    }

    public void searchByName(String name) {
        AddressBookEntry result = nameIndex.search(name.trim());
        if (result != null) {
            System.out.println("Entry found:");
            result.display();
        } else {
            System.out.println("Entry with name '" + name + "' not found.");
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            outputStream.writeObject(entries);
            System.out.println("Address book saved successfully.");
        } catch (IOException e) {
            System.err.println("Error: Could not save address book to file.");
            e.printStackTrace();
        }
    }

    public void displayEntries() {
        if (entries.isEmpty()) {
            System.out.println("No entries found in the address book.");
        } else {
            entries.forEach(AddressBookEntry::display);
        }
    }
}
