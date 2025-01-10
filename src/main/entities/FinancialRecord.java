package main.entities;

import java.time.LocalDate;

public class FinancialRecord {
    private String from;
    private double amount;
    private LocalDate date;

    public String getFrom() {
        return from;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public FinancialRecord(String from, double amount, LocalDate date) {
        this.from = from;
        this.amount = amount;
        this.date = date;
    }
}
