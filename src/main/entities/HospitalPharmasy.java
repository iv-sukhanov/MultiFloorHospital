package main.entities;

import java.time.LocalDate;

public class HospitalPharmasy extends Hospital {
    
    private String name;
    private double price;
    private LocalDate expirationDate;

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HospitalPharmasy(Hospital hospital, String name, double price, LocalDate expirationDate) {
        super(hospital);

        setName(name);
        setPrice(price);
        setExpirationDate(expirationDate);
    }
}
