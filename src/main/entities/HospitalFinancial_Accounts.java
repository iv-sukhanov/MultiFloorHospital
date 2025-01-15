package main.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the financial accounts of a hospital.
 */
public class HospitalFinancial_Accounts extends Hospital {
    private List<FinancialRecord> financialRecords = new LinkedList<>();
    private double balance;

    /**
     * Constructs a HospitalFinancial_Accounts object.
     *
     * @param hospital the hospital associated with the financial accounts
     * @param balance the initial balance of the financial accounts
     */
    public HospitalFinancial_Accounts(Hospital hospital, double balance) {
        super(hospital);
        this.balance = balance;
    }

    /**
     * Returns the current balance of the financial accounts.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the financial accounts.
     *
     * @param balance the new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns the details of all financial records.
     *
     * @return a 2D array containing the details of the financial records
     */
    public String[][] getDetails() {
        
        String[][] output = new String[financialRecords.size()][3];
        for (int i = 0; i < financialRecords.size() ; i++) {
            output[i][0] = financialRecords.get(i).getDate().toString();
            output[i][1] = financialRecords.get(i).getFrom();
            output[i][2] = String.valueOf(financialRecords.get(i).getAmount());
        }

        return output;
    }

    /**
     * Adds a financial record and updates the balance.
     *
     * @param record the financial record to add
     */
    public void addRecord(FinancialRecord record) {
        balance += record.getAmount();
        financialRecords.add(record);
    }
}
