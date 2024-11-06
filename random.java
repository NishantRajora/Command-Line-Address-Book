import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Class representing an address book entry generator
public class random {
    private static final int NUM_ENTRIES = 100000;

    // Generate random phone number
    private static String generatePhoneNumber(Random random) {
        return String.format("%03d-%03d-%04d", random.nextInt(1000), random.nextInt(1000), random.nextInt(10000));
    }

    // Generate random address
    private static String generateAddress(Random random) {
        return random.nextInt(9999) + " Example St, City, State, " + (random.nextInt(90000) + 10000);
    }

    // Generate a random entry
    private static AddressBookEntry generateRandomEntry(int id, Random random) {
        String name = "Person " + id;
        String phone = generatePhoneNumber(random);
        String email = "person" + id + "@example.com";
        String address = generateAddress(random);
        return new AddressBookEntry(name, phone, email, address);
    }

    // Main method to generate the file
    public static void main(String[] args) {
        Random random = new Random();
        List<AddressBookEntry> entries = new ArrayList<>();

        // Generate random entries
        for (int i = 1; i <= NUM_ENTRIES; i++) {
            entries.add(generateRandomEntry(i, random));
        }

        // Save entries to address_book.dat
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("address_book.dat"))) {
            outputStream.writeObject(entries);
            System.out.println("Generated and saved " + NUM_ENTRIES + " entries to address_book.dat.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
