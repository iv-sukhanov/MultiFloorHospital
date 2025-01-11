package main.entities;

import java.time.LocalDate;

/**
 * Represents a pharmacy item in a hospital.
 */
public class HospitalPharmasy extends Hospital {
    
    private String name;
    private double price;
    private LocalDate expirationDate;

    /**
     * Returns the expiration date of the pharmacy item.
     *
     * @return the expiration date
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the pharmacy item.
     *
     * @param expirationDate the new expiration date
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the name of the pharmacy item.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pharmacy item.
     *
     * @param name the new name
     * @throws IllegalArgumentException if the name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    /**
     * Returns the price of the pharmacy item.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the pharmacy item.
     *
     * @param price the new price
     * @throws IllegalArgumentException if the price is negative
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    /**
     * Constructs a HospitalPharmasy object.
     *
     * @param hospital the hospital associated with the pharmacy item
     * @param name the name of the pharmacy item
     * @param price the price of the pharmacy item
     * @param expirationDate the expiration date of the pharmacy item
     */
    public HospitalPharmasy(Hospital hospital, String name, double price, LocalDate expirationDate) {
        super(hospital);
        setName(name);
        setPrice(price);
        setExpirationDate(expirationDate);
    }
}
