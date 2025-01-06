package main.entities;

import java.time.LocalDate;

public class HospitalStaff extends Person {
    
    private String position;
    private boolean available;

    public String getPosition() {
        return position;
    }

    public boolean isAvailable() {
        return available;
    }

    public Patient getPatient() {
        return patient;
    }

    Patient patient;

    public HospitalStaff(
            String name,
            int age,
            LocalDate dateOfBirth,
            String idNumber,
            String phoneNumber,
            String email,
            boolean isMale,
            boolean ownsCar,
            String carNumber,
            String position,
            boolean available,
            Patient patient
    ) {
        super(name, age, dateOfBirth, idNumber, phoneNumber, email, isMale, ownsCar, carNumber);
        this.position = position;
        this.available = available;
        this.patient = patient;
    }
}
