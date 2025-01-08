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

        if (assignedRoom == null) {
            throw new IllegalArgumentException("Room should be specified");
        }

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

        if (assignedBed == null) {
            throw new IllegalArgumentException("Bed sould be specified");
        }

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
        setDiagnosis(diagnosis);
        if (assignedDoctor != null) {
            assignedDoctor.setAvailable(false);
            assignedDoctor.setPatient(this);
        }
        setAssignedDoctor(assignedDoctor);
        setAssignedRoom(assignedRoom);
        if (assignedBed != null) {
            assignedBed.setAvailable(false);
            assignedBed.setPatient(this);
        }
        setAssignedBed(assignedBed);
        if (assignedEquipment != null) {
            assignedEquipment.setAvailable(false);
        }
        setAssignedEquipment(assignedEquipment);
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