package main.entities;

public class HospitalFinancial_Accounts extends Hospital {
    private double balance;

    public HospitalFinancial_Accounts(Hospital hospital, double balance) {
        super(hospital);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}
