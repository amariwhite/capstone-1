package com.pluralsight;

import java.util.Scanner;

public class CliApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("\nHOME MENU: ");
            System.out.println("(D) Add Deposit");
            System.out.println("(P) Make Payment (Debit)");
            System.out.println("(L) Ledger");
            System.out.println("(X) Exit");
            System.out.println("Select an Option");
            String choice = scanner.nextLine();

            switch (choice){
                case "D":
                    addTransaction(scanner, true);
                    break;
                case "P":
                    addTransaction(scanner, false);
                    break;
                case "L":
                    showLedger();
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Selection. ");
            }
        }
        scanner.close();
        System.out.println("BYE BYE!");


     private static void addTransaction(Scanner scanner, boolean isDeposit){
            System.out.println("Date (MM-DD-YYYY): ");
            String date = scanner.nextLine();
            System.out.println("Time (HH:MM:SS): ");
            String time = scanner.nextLine();
            System.out.println("Description: ");
            String description = scanner.nextLine();
            System.out.println("Vendor: ");
            String vendor = scanner.nextLine();
            System.out.println("Amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (isDeposit) amount = Math.abs(amount);

            Transaction t  = new Transaction(date, time, description, vendor, amount);
            TransactionService.saveTransaction(t);
            System.out.println("Transaction Saved!");

        }

    }
    public static void showHomeScreen(){
        System.out.println("Bank Ledger");
        System.out.println("---------------------");
        System.out.println("\nWhat do you want to do?");
        System.out.println(" D- Add Deposit ");
        System.out.println(" P- Make Payment (Debit)");
        System.out.println(" L- Ledger");
        System.out.println(" 4- Exit Application");
        System.out.print("Enter command: ");

    }
    public static void addDeposit(){

    }

}
