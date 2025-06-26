# 📇 Command-Line Address Book

A **Java-based command-line address book** built as a 3rd semester **DSA project**, supporting fast search, add, delete, save, and restore operations — optimized to work with **up to a million entries** under **one second** using efficient data structures like **B+ Trees** and advanced **string matching algorithms**.

---

## 🎯 Objective

To implement a **command-line address book** in Java that:
- Supports **adding**, **deleting**, **searching**
- Saves and restores data from file
- Uses **B+ Tree** for fast external searching
- Incorporates **KMP** or **Rabin–Karp** for optimized string matching

---

## 🧰 Features

- 📥 Add a new contact
- ❌ Delete an existing contact
- 🔍 Fast search by name (using KMP or Rabin–Karp)
- 💾 Save address book data to file
- ♻️ Restore data from file on program start
- ⚡ Efficient search under **1 second** for **1 million entries**

---

## 🏗️ Technologies Used

| Feature                  | Technology / Algorithm         |
|--------------------------|-------------------------------|
| Language                 | Java                          |
| Data Structure           | B+ Tree                       |
| String Matching          | KMP / Rabin–Karp              |
| File Handling            | Java I/O (`java.io`)          |
| Persistent Storage       | Plain text / serialized files |

---

## 📁 Project Structure

```
📦 CommandLineAddressBook/
├── AddressBookEntry.java     // Class for address book entries
├── BPlusTreeNode.java        // Class for B+ Tree node structure
├── BPlusTree.java            // Insert and search logic for B+ Tree
├── AddressBook.java          // Manages entries and indexing using B+ Tree
└── Main.java                 // Entry point: CLI interaction
```

---

## 🧑‍💻 How to Run

### 🔧 Compile
```bash
javac *.java
```

### 🚀 Execute
```bash
java Main
```

---

## 💾 File Format (Example)

```

Name: John Doe
Phone: 9123456780
Email: john@example.com
Address: 56 Park Ave, Delhi
---
```

---

## 🚀 Algorithms in Use

### 📂 External Searching
- **B+ Tree**: Efficient for indexing and searching entries in disk-based systems

### 🔎 String Matching
- **KMP (Knuth-Morris-Pratt)** or **Rabin–Karp**: For fast substring search in names

---

## 🧪 Performance

- ⚡ Capable of handling up to **1,000,000 entries**
- ⏱️ Search operations complete in **< 1 second**

---

## 🔐 Future Enhancements

- 🖥️ GUI using **JavaFX** or **Swing**
- 🔐 User authentication & multi-user support
- ☁️ Cloud-based file sync or database integration
- 📊 Advanced filters and grouping (by city, tags, etc.)

---

## ⚠️ Disclaimer

> This project is built for academic learning purposes only.  
> It is not intended for production or commercial use.

---

## 📚 References

- B+ Tree Explanation – GeeksforGeeks, TutorialsPoint
- KMP and Rabin–Karp – CLRS, NPTEL Lectures
