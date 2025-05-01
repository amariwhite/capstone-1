
package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Transactions {
    private static final String filePath = "transactions.csv";

    public static void addTransaction(boolean isDeposit) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (!isDeposit) amount *= -1;

        LocalDateTime now = LocalDateTime.now();
        String record = String.format("%s|%s|%s|%s|%.2f", now.toLocalDate(), now.toLocalTime().withNano(0), description, vendor, amount);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(record);
            writer.newLine();
            System.out.println("Transaction recorded.");
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public static List<String[]> readAllTransactions() {
        List<String[]> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) transactions.add(parts);
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        return transactions;
    }

    public static void displayTransactions(List<String[]> transactions) {
        transactions.stream()
                .sorted((a, b) -> b[0].compareTo(a[0]) != 0 ? b[0].compareTo(a[0]) : b[1].compareTo(a[1]))
                .forEach(t -> System.out.printf("%s %s | %s | %s | %.2f%n", t[0], t[1], t[2], t[3], Double.parseDouble(t[4])));
    }

    public static List<String[]> filterTransactionsByAmount(boolean isDeposit) {
        List<String[]> all = readAllTransactions();
        return all.stream()
                .filter(t -> {
                    double amount = Double.parseDouble(t[4]);
                    return isDeposit ? amount > 0 : amount < 0;
                }).toList();
    }

    public static List<String[]> filterByVendor(String vendor) {
        return readAllTransactions().stream()
                .filter(t -> t[3].equalsIgnoreCase(vendor))
                .toList();
    }

    public static List<String[]> filterByDateRange(LocalDateTime start, LocalDateTime end) {
        return readAllTransactions().stream()
                .filter(t -> {
                    LocalDateTime dateTime = LocalDateTime.parse(t[0] + "T" + t[1]);
                    return !dateTime.isBefore(start) && !dateTime.isAfter(end);
                }).toList();
    }
}
