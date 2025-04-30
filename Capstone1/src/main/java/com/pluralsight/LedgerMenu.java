package com.pluralsight;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.security.cert.CertPath;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LedgerMenu {
    private static String transactionsFile = "src/data/Transactions.csv";
    private Scanner scanner;

public LedgerMenu(){
    scanner = new Scanner(System.in);
}
public void addDeposit(){
    System.out.println("\n--- Add Deposit ---");
    LocalDate date = getCurrentDate();

    System.out.println("Enter Description: ");
    String description = scanner.nextLine();

    System.out.println("Enter Deposit Amount: ");
    double amount = scanner.nextDouble();

    System.out.println("Enter Vendor Name: ");
    String vendor = scanner.nextLine();

    Tranaction deposit = new Tranaction(date, description, amount, vendor);
    saveTransaction(deposit);
    System.out.println("Deposit added Complete!");
}
public void makePayment(){
    System.out.println("\n--- Make Payment ---");
    LocalDate date = getCurrentDate();

    System.out.println("Enter Payment Description: ");
    String description = scanner.nextLine();

    System.out.println("Enter Payment Amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine();

    System.out.println("Enter Vendor Name: ");
    String vendor = scanner.nextLine();

    Tranaction payment = new Tranaction(date,description,amount,vendor);
    saveTransaction(payment);
    System.out.println("Payment Recorded Complete!");
}
private LocalDate getCurrentDate(){
    return LocalDate.now();
}
private void saveTransaction(Tranaction tranaction){
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsFile, true))){
        writer.write(tranaction.toString());
        writer.newLine();
    }catch (IOException e){
        System.out.println("Error Saving Transaction: " + e.getMessage());
    }
}
public void displayLedger(){
    System.out.println("\n--- Ledger Menu ---");
    System.out.println("A All Entries");
    System.out.println("D Deposits");
    System.out.println("P Payments");
    System.out.println("R Reports");
    System.out.println("H Home");

    String choice = scanner.nextLine();
    switch (choice){
        case "A":
            displayTransactions(getAllTransactions());
            break;
        case "D":
            displayTransactions(getDeposits());
            break;
        case "P":
            displayTransactions(getPayments());
            break;
        case "R":
            runReports();
            break;
        case "H":
            return;

    }
}

    private ArrayList<Tranaction> getAllTransactions(){
        return readTransactions();
    }

    private ArrayList<Tranaction> getDeposits(){
        return readTransactions().stream()
                .filter(t -> t.getAmount()< 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Tranaction> getPayments(){
        return readTransactions().stream()
                .filter(t -> t.getAmount() > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Tranaction> readTransactions(){
        ArrayList<Tranaction> tranactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(transactionsFile))){
            String line;
            while ((line =reader.readLine()) != null){
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                String description = parts[1];
                double amount = Double.parseDouble(parts[2]);
                String vendor = parts[3];
                tranactions.add(new Tranaction(date,description,amount,vendor));
            }
        }catch (IOException e){
            System.out.println("Error Reading Transactions: " + e.getMessage());
        }
        return tranactions;
    }

    private void displayTransactions(ArrayList<Tranaction> tranactions){
        tranactions.sort((t1, t2) -> t2.getDate().compareTo(t1.getDate()));
        for (Tranaction t : tranactions) {
            System.out.printf("%s | %s | $%.2f | %s%n", t.getDate(), t.getDescription(), t.getAmount(), t.getVendor());

        }
    }

    private void runReports(){
        while (true){
            System.out.println("\n--- Reports ---");
            System.out.println("1 Month To Date");
            System.out.println("2 Previous Month");
            System.out.println("3 Year To Date");
            System.out.println("4 Previous Year");
            System.out.println("5 Search By Vendor");
            System.out.println("0 Back");

            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    displayMonthToDateReport();
                    break;
                case "2":
                    displayPreviousMonthReport();
                    break;
                case "3":
                    displayYearToDateReport();
                    break;
                case "4":
                    displayPreviousYearReport();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    return;
            }
        }
    }

    private void searchByVendor(){
        System.out.println("Enter Vendor Name: ");
        String vendorName = scanner.nextLine();

        ArrayList<Tranaction> vendorTransactions = readTransactions().stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendorName))
                .collect(Collectors.toCollection(ArrayList::new));
        displayTransactions(vendorTransactions);

    }

    private void displayMonthToDateReport(){
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);

        ArrayList<Tranaction> monthToDateTransactions = readTransactions().stream()
                .filter(t -> !t.getDate().isBefore(startOfMonth) && !t.getDate().isAfter(now))
                .collect(Collectors.toCollection(ArrayList::new));
        displayTransactions(monthToDateTransactions);
    }

    private void displayPreviousMonthReport(){}

    private void displayYearToDateReport(){}

    private void displayPreviousYearReport(){}

    public void addDeposits() {
        System.out.println("\n--- Add Deposit ---");
        LocalDate date = getCurrentDate();

        System.out.println("Enter Description: ");
        String description = scanner.nextLine();

        System.out.println("Enter Deposit Amount: ");
        double amount = scanner.nextDouble();

        System.out.println("Enter Vendor Name: ");
        String vendor = scanner.nextLine();

        Tranaction deposit = new Tranaction(date, description, amount, vendor);
        saveTransaction(deposit);
        System.out.println("Deposit added Complete!");

    }
}
