#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <cstring> // for std::memcpy

// Define constants for field sizes
const size_t NAME_SIZE = 50;
const size_t PHONE_SIZE = 15;
const size_t EMAIL_SIZE = 50;
const size_t ADDRESS_SIZE = 100;

// Struct to represent an address book entry
struct AddressBookEntry {
    char name[NAME_SIZE];
    char phone[PHONE_SIZE];
    char email[EMAIL_SIZE];
    char address[ADDRESS_SIZE];

    // Method to display the entry
    void display() const {
        std::cout << "Name: " << name
                  << ", Phone: " << phone
                  << ", Email: " << email
                  << ", Address: " << address << std::endl;
    }
};

// Class to manage the address book
class AddressBook {
private:
    std::vector<AddressBookEntry> entries;

public:
    // Load address book from a binary file
    void loadFromFile(const std::string& filename) {
        std::ifstream file(filename, std::ios::binary);
        if (!file.is_open()) {
            std::cerr << "Error: Could not open file " << filename << std::endl;
            return;
        }

        AddressBookEntry entry;
        while (file.read(reinterpret_cast<char*>(&entry), sizeof(AddressBookEntry))) {
            entries.push_back(entry);
        }

        file.close();
        std::cout << "Address book loaded successfully with " << entries.size() << " entries." << std::endl;
    }

    // Save address book to a binary file
    void saveToFile(const std::string& filename) {
        std::ofstream file(filename, std::ios::binary);
        if (!file.is_open()) {
            std::cerr << "Error: Could not open file " << filename << std::endl;
            return;
        }

        for (const auto& entry : entries) {
            file.write(reinterpret_cast<const char*>(&entry), sizeof(AddressBookEntry));
        }

        file.close();
        std::cout << "Address book saved successfully." << std::endl;
    }

    // Add a new entry to the address book
    void addEntry(const AddressBookEntry& entry) {
        entries.push_back(entry);
    }

    // Display all entries
    void displayEntries() const {
        for (const auto& entry : entries) {
            entry.display();
        }
    }

    // Get the number of entries
    size_t getEntryCount() const {
        return entries.size();
    }
};

int main() {
    AddressBook addressBook;
    std::string filename = "address_book.dat"; // Path to your binary data file

    // Load entries from the binary file (which should have the random data)
    addressBook.loadFromFile(filename);

    // Display loaded entries
    std::cout << "Displaying all address book entries:" << std::endl;
    addressBook.displayEntries();

    return 0;
}
