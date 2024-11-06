# Command-Line-Address-Book
DSA 3rd sem project
To implement a command-line address book in Java that supports adding, deleting, searching, saving to a file, and restoring data while ensuring efficient search operations (under one second for up to a million entries), you can use a B-Tree or B+ Tree for external searching and file handling for persistence. Additionally, you can incorporate string matching algorithms like KMP or Rabin-Karp to speed up name searches.


To organize this program, let's split the code into different Java files:

AddressBookEntry.java - Class for address book entries.
BPlusTreeNode.java - Class for nodes of the B+ Tree.
BPlusTree.java - Class for the B+ Tree with insert and search methods.
AddressBook.java - Main address book class that manages entries and B+ Tree indexing.
Main.java - Main class to run the program and interact with the user.