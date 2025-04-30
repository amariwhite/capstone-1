package com.pluralsight;

import java.util.Scanner;

public class BankLedgerApp {
    public static void main(String[] args) {
        LedgerMenu ledgerMenu = new LedgerMenu();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n--- Bank Ledger App ---");
            System.out.println("D Add Deposit");
            System.out.println("P Make Payment (Debit)");
            System.out.println("L Ledger");
            System.out.println("X Exit");

            String choice = scanner.nextLine();
            switch (choice){
                case "D":
                    ledgerMenu.addDeposits();
                    break;
                case "P":
                    ledgerMenu.makePayment();
                    break;
                case "L":
                    ledgerMenu.displayLedger();
                    break;
                case "X":
                    System.out.println("Exiting Bank Ledger App, Bye!");
                    return;
                default:
                    System.out.println("Invalid Selection, Try Again.");
            }
        }

    }
}
