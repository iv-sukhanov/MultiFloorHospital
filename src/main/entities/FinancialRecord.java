package main.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class FinancialRecord implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
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
