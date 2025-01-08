package main.entities;

import java.time.LocalDate;

public class HospitalStaff extends Person {
    
    private String position;
    private boolean available;
    Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPosition() {
        return position;
    }

    public boolean isAvailable() {
        return available;
    }

    public Patient getPatient() {
        return patient;
    }


    public HospitalStaff(
            String name,
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
        super(name, dateOfBirth, idNumber, phoneNumber, email, isMale, ownsCar, carNumber);
        this.position = position;
        this.available = available;
        
        if (patient != null) {
            patient.setAssignedDoctor(this);
        }
        this.patient = patient;
    }
}
