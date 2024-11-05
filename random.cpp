#include <iostream>
#include <fstream>
#include <random>
#include <string>
#include <cstring> // for std::memcpy

// Constants for entry sizes
const size_t NAME_SIZE = 50;
const size_t PHONE_SIZE = 15;
const size_t EMAIL_SIZE = 50;
const size_t ADDRESS_SIZE = 100;
const size_t NUM_ENTRIES = 100000; // Number of entries to generate

// Function to generate a random phone number
std::string generatePhoneNumber() {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> distr1(100, 999);
    std::uniform_int_distribution<> distr2(100, 999);
    std::uniform_int_distribution<> distr3(1000, 9999);
    
    return std::to_string(distr1(gen)) + "-" + std::to_string(distr2(gen)) + "-" + std::to_string(distr3(gen));
}

// Function to generate a random address
std::string generateAddress() {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> streetNum(1, 9999);
    
    return std::to_string(streetNum(gen)) + " Sample St, City, State, " + std::to_string(rd() % 90000 + 10000); // Random ZIP code
}

// Main function to generate the address book file
int main() {
    std::ofstream file("address_book.dat", std::ios::binary);
    if (!file.is_open()) {
        std::cerr << "Error: Could not open file for writing." << std::endl;
        return 1;
    }

    for (size_t i = 1; i <= NUM_ENTRIES; ++i) {
        // Generate random data for each entry
        std::string name = "Person " + std::to_string(i);
        std::string phone = generatePhoneNumber();
        std::string email = "person" + std::to_string(i) + "@example.com";
        std::string address = generateAddress();

        // Create a fixed-size byte array for each entry
        char entry[NAME_SIZE + PHONE_SIZE + EMAIL_SIZE + ADDRESS_SIZE] = {0};
        
        std::memcpy(entry, name.c_str(), name.size());
        std::memcpy(entry + NAME_SIZE, phone.c_str(), phone.size());
        std::memcpy(entry + NAME_SIZE + PHONE_SIZE, email.c_str(), email.size());
        std::memcpy(entry + NAME_SIZE + PHONE_SIZE + EMAIL_SIZE, address.c_str(), address.size());
        
        // Write the entry to the file
        file.write(entry, sizeof(entry));
    }

    file.close();
    std::cout << "Successfully generated " << NUM_ENTRIES << " entries in address_book.dat." << std::endl;
    return 0;
}
