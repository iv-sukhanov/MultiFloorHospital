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

    public String[][] getDetails() {
        
        String[][] output = new String[financialRecords.size()][3];
        for (int i = financialRecords.size() - 1; i >= 0; i--) {
            output[i][0] = financialRecords.get(i).getDate().toString();
            output[i][1] = financialRecords.get(i).getFrom();
            output[i][2] = String.valueOf(financialRecords.get(i).getAmount());
        }

        return output;
    }

    public void addRecord(FinancialRecord record) {
        balance += record.getAmount();
        financialRecords.add(record);
    }
}
