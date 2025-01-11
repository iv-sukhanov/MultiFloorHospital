package main.entities;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The FinancialRecord is an immutable class that represents a financial transaction record.
 * It includes details about the source, amount, and date of the transaction.
 */
public class FinancialRecord implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String from;
    private double amount;
    private LocalDate date;

    /**
     * Gets the source of the transaction.
     * 
     * @return the source of the transaction
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the amount of the transaction.
     * 
     * @return the amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the date of the transaction.
     * 
     * @return the date of the transaction
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Constructs a new FinancialRecord with the specified source, amount, and date.
     * 
     * @param from the source of the transaction
     * @param amount the amount of the transaction
     * @param date the date of the transaction
     */
    public FinancialRecord(String from, double amount, LocalDate date) {
        this.from = from;
        this.amount = amount;
        this.date = date;
    }
}
