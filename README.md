# capstone-1
# 💰 Bank Ledger CLI Application

## 📌 Project Description

The **Bank Ledger CLI Application** is a Java-based console program that allows users to track financial transactions like deposits and payments. This application is designed for small businesses or personal users who want a lightweight and straightforward solution to manage their transaction history.

All transactions are stored in a `transactions.csv` file in the format:


The application supports:
- Adding deposits and payments  
- Viewing all transactions in a ledger  
- Filtering by deposits, payments, vendor, or time range  
- Running built-in reports like month-to-date, previous month, year-to-date, and more  

---

---

## 💡 Interesting Code Highlight

One of the most interesting parts of the project is the **transaction filtering and display** logic, which sorts transactions by date and time and uses Java’s built-in data structures efficiently.

```java
public static void displayTransactions(List<String[]> transactions) {
    transactions.sort((a, b) -> {
        LocalDateTime dateTimeA = LocalDateTime.parse(a[0] + "T" + a[1]);
        LocalDateTime dateTimeB = LocalDateTime.parse(b[0] + "T" + b[1]);
        return dateTimeB.compareTo(dateTimeA); // Newest first
    });

    System.out.println("\nDate       | Time     | Description         | Vendor         | Amount");
    System.out.println("---------------------------------------------------------------------");
    for (String[] entry : transactions) {
        System.out.printf("%s | %s | %-20s | %-15s | %8.2f%n",
                entry[0], entry[1], entry[2], entry[3], Double.parseDouble(entry[4]));
    }
}
javac BankLedgerApp.java Transactions.java LedgerMenu.java
java BankLedgerApp

---

---

project-folder/
│
├── BankLedgerApp.java       # Main application class
├── Transactions.java        # File handling & transaction logic
├── LedgerMenu.java          # Ledger and reports UI
├── transactions.csv         # Data file 
├── README.md                # This file

---

---

Created by Amari White. This project was developed for a Java CLI application Capstone.
