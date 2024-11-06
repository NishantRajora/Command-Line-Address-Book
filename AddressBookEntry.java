import java.io.Serializable;

public class AddressBookEntry implements Serializable {
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

    // Getters for B+ tree indexing and other operations
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
}
