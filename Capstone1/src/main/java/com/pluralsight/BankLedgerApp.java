package com.pluralsight;

import java.util.Scanner;

public class BankLedgerApp {
    public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                boolean running = true;

                while (running) {
                    System.out.println("\n--- Bank Ledger App ---");
                    System.out.println("D) Add Deposit");
                    System.out.println("P) Make Payment (Debit)");
                    System.out.println("L) Ledger");
                    System.out.println("X) Exit");
                    System.out.print("Enter option: ");

                    String choice = scanner.nextLine().trim().toUpperCase();
                    switch (choice) {
                        case "D":
                            Transactions.addTransaction(true);
                            break;
                        case "P":
                            Transactions.addTransaction(false);
                            break;
                        case "L":
                            LedgerMenu.displayLedgerMenu();
                            break;
                        case "X":
                            System.out.println("Goodbye!");
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            }

}