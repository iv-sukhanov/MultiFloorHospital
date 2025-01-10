package main.entities;

import java.util.LinkedList;
import java.util.List;

public class HospitalFinancial_Accounts extends Hospital {
    private List<FinancialRecord> financialRecords = new LinkedList<>();
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
