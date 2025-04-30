package com.pluralsight;

import java.time.LocalDate;

class Tranaction{
    private LocalDate date;
    private String description;
    private double amount;
    private String vendor;

    public Tranaction(LocalDate date, String description, String vendor, double amount) {
        this.date = date;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Tranaction(LocalDate date, String description, double amount, String vendor) {
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Tranaction{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", vendor='" + vendor + '\'' +
                '}';
    }
}