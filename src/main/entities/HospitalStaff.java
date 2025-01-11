package main.entities;

import java.time.LocalDate;

/**
 * Represents a staff member in a hospital.
 */
public class HospitalStaff extends Person {
    
    private String position;
    private boolean available;
    private Patient patient;

    /**
     * Sets the position of the staff member.
     *
     * @param position the new position
     * @throws IllegalArgumentException if the position is null or empty
     */
    public void setPosition(String position) {
        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException("Position cannot be empty");
        }
        this.position = position;
    }

    /**
     * Sets the patient assigned to the staff member.
     *
     * @param patient the patient to assign
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Sets the availability of the staff member.
     *
     * @param available the new availability status
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Returns the position of the staff member.
     *
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Returns the availability of the staff member.
     *
     * @return true if the staff member is available, false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Returns the patient assigned to the staff member.
     *
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Constructs a HospitalStaff object.
     *
     * @param name the name of the staff member
     * @param dateOfBirth the date of birth of the staff member
     * @param idNumber the ID number of the staff member
     * @param phoneNumber the phone number of the staff member
     * @param email the email of the staff member
     * @param isMale the gender of the staff member
     * @param ownsCar whether the staff member owns a car
     * @param carNumber the car number of the staff member
     * @param position the position of the staff member
     * @param available the availability status of the staff member
     * @param patient the patient assigned to the staff member
     */
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
