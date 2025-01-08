package main.entities;

import java.time.LocalDate;

public class Patient extends Person {
    
    private String diagnosis;
    private HospitalStaff assignedDoctor;
    private HospitalEquipment assignedEquipment;
    private HospitalBed assignedBed;
    private HospitalRoom assignedRoom;
    
    public HospitalRoom getAssignedRoom() {
        return assignedRoom;
    }

    public void setAssignedRoom(HospitalRoom assignedRoom) {
        this.assignedRoom = assignedRoom;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public HospitalStaff getAssignedDoctor() {
        return assignedDoctor;
    }

    public void setAssignedDoctor(HospitalStaff assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public HospitalBed getAssignedBed() {
        return assignedBed;
    }

    public void setAssignedBed(HospitalBed assignedBed) {
        this.assignedBed = assignedBed;
    }

    public HospitalEquipment getAssignedEquipment() {
        return assignedEquipment;
    }

    public void setAssignedEquipment(HospitalEquipment assignedEquipment) {
        this.assignedEquipment = assignedEquipment;
    }

    public Patient(
            String name,
            LocalDate dateOfBirth,
            String idNumber,
            String phoneNumber,
            String email,
            boolean gender, 
            boolean ownsCar,
            String carNumber,
            String diagnosis,
            HospitalStaff assignedDoctor,
            HospitalRoom assignedRoom,
            HospitalBed assignedBed,
            HospitalEquipment assignedEquipment
    ) {
        super(name, dateOfBirth, idNumber, phoneNumber, email, gender, ownsCar, carNumber);
        this.diagnosis = diagnosis;
        if (assignedDoctor != null) {
            assignedDoctor.setAvailable(false);
            assignedDoctor.setPatient(this);
        }
        this.assignedDoctor = assignedDoctor;
        this.assignedRoom = assignedRoom;
        if (assignedBed != null) {
            assignedBed.setAvailable(false);
            assignedBed.setPatient(this);
        }
        this.assignedBed = assignedBed;
        if (assignedEquipment != null) {
            assignedEquipment.setAvailable(false);
        }
        this.assignedEquipment = assignedEquipment;
    }

    public void clean() {

        if (assignedDoctor != null) {
            assignedDoctor.setAvailable(true);
            assignedDoctor.setPatient(null);
        }

        if (assignedBed != null) {
            assignedBed.setAvailable(true);
            assignedBed.setPatient(null);
        }

        if (assignedEquipment != null) {
            assignedEquipment.setAvailable(true);
        }
    }
}