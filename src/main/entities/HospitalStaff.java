package main.entities;

import java.time.LocalDate;

public class HospitalStaff extends Person {
    
    private String position;
    private boolean available;
    private Patient patient;
        

    public void setPosition(String position) {

        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException("Position cannot be empty");
        }

        this.position = position;
    }

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
        setPosition(position);
        setAvailable(available);
        
        if (patient != null) {
            patient.setAssignedDoctor(this);
        }
        setPatient(patient);
    }
}
