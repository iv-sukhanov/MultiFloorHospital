package main.entities;

import java.time.LocalDate;

/**
 * Represents a patient in a hospital.
 */
public class Patient extends Person {
    
    private String diagnosis;
    private HospitalStaff assignedDoctor;
    private HospitalEquipment assignedEquipment;
    private HospitalBed assignedBed;
    private HospitalRoom assignedRoom;

    /**
     * Returns the room assigned to the patient.
     *
     * @return the assigned room
     */
    public HospitalRoom getAssignedRoom() {
        return assignedRoom;
    }

    /**
     * Sets the room assigned to the patient.
     *
     * @param assignedRoom the room to assign
     * @throws IllegalArgumentException if the room is null
     */
    public void setAssignedRoom(HospitalRoom assignedRoom) {
        if (assignedRoom == null) {
            throw new IllegalArgumentException("Room should be specified");
        }
        this.assignedRoom = assignedRoom;
    }

    /**
     * Returns the diagnosis of the patient.
     *
     * @return the diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the diagnosis of the patient.
     *
     * @param diagnosis the new diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Returns the doctor assigned to the patient.
     *
     * @return the assigned doctor
     */
    public HospitalStaff getAssignedDoctor() {
        return assignedDoctor;
    }

    /**
     * Sets the doctor assigned to the patient.
     *
     * @param assignedDoctor the doctor to assign
     */
    public void setAssignedDoctor(HospitalStaff assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    /**
     * Returns the bed assigned to the patient.
     *
     * @return the assigned bed
     */
    public HospitalBed getAssignedBed() {
        return assignedBed;
    }

    /**
     * Sets the bed assigned to the patient.
     *
     * @param assignedBed the bed to assign
     * @throws IllegalArgumentException if the bed is null
     */
    public void setAssignedBed(HospitalBed assignedBed) {
        if (assignedBed == null) {
            throw new IllegalArgumentException("Bed should be specified");
        }
        this.assignedBed = assignedBed;
    }

    /**
     * Returns the equipment assigned to the patient.
     *
     * @return the assigned equipment
     */
    public HospitalEquipment getAssignedEquipment() {
        return assignedEquipment;
    }

    /**
     * Sets the equipment assigned to the patient.
     *
     * @param assignedEquipment the equipment to assign
     */
    public void setAssignedEquipment(HospitalEquipment assignedEquipment) {
        this.assignedEquipment = assignedEquipment;
    }

    /**
     * Constructs a Patient object.
     *
     * @param name the name of the patient
     * @param dateOfBirth the date of birth of the patient
     * @param idNumber the ID number of the patient
     * @param phoneNumber the phone number of the patient
     * @param email the email of the patient
     * @param gender the gender of the patient
     * @param ownsCar whether the patient owns a car
     * @param carNumber the car number of the patient
     * @param diagnosis the diagnosis of the patient
     * @param assignedDoctor the doctor assigned to the patient
     * @param assignedRoom the room assigned to the patient
     * @param assignedBed the bed assigned to the patient
     * @param assignedEquipment the equipment assigned to the patient
     */
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

    /**
     * Cleans up the patient's assignments, making the doctor, bed, and equipment available.
     * Instead of finalize(), which is deprecated.))))))))))))
     */
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