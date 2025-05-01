
package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class LedgerMenu {
    public static void displayLedgerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Ledger Menu ---");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter option: ");

            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A":
                    Transactions.displayTransactions(Transactions.readAllTransactions());
                    break;
                case "D":
                    Transactions.displayTransactions(Transactions.filterTransactionsByAmount(true));
                    break;
                case "P":
                    Transactions.displayTransactions(Transactions.filterTransactionsByAmount(false));
                    break;
                case "R":
                    runReports();
                    break;
                case "H":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void runReports() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Reports ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Enter option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    LocalDateTime startMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
                    LocalDateTime now = LocalDateTime.now();
                    Transactions.displayTransactions(Transactions.filterByDateRange(startMonth, now));
                    break;
                case "2":
                    LocalDate lastMonthStart = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                    LocalDateTime lastMonthStartTime = lastMonthStart.atStartOfDay();
                    LocalDateTime lastMonthEndTime = lastMonthStart.plusMonths(1).minusDays(1).atTime(23, 59, 59);
                    Transactions.displayTransactions(Transactions.filterByDateRange(lastMonthStartTime, lastMonthEndTime));
                    break;
                case "3":
                    LocalDateTime startYear = LocalDate.now().withDayOfYear(1).atStartOfDay();
                    Transactions.displayTransactions(Transactions.filterByDateRange(startYear, LocalDateTime.now()));
                    break;
                case "4":
                    LocalDate lastYearStart = LocalDate.now().minusYears(1).withDayOfYear(1);
                    LocalDate lastYearEnd = lastYearStart.withMonth(12).withDayOfMonth(31);
                    Transactions.displayTransactions(Transactions.filterByDateRange(lastYearStart.atStartOfDay(), lastYearEnd.atTime(23, 59, 59)));
                    break;
                case "5":
                    System.out.print("Enter vendor name: ");
                    String vendor = scanner.nextLine();
                    Transactions.displayTransactions(Transactions.filterByVendor(vendor));
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}


