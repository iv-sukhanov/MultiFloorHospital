package main.entities;

/**
 * The HospitalBed class represents a bed in a hospital.
 * It extends the Hospital class and includes details about the bed's availability and the patient assigned to it.
 */
public class HospitalBed extends Hospital {
    private int bedNumber;
    private boolean isAvailable;
    private Patient patient;

    /**
     * Gets the bed number.
     * 
     * @return the bed number
     */
    public int getBedNumber() {
        return bedNumber;
    }

    /**
     * Sets the bed number.
     * 
     * @param bedNumber the new bed number
     */
    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    /**
     * Checks if the bed is available.
     * 
     * @return true if the bed is available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of the bed.
     * 
     * @param isAvailable the new availability status
     */
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * Gets the patient assigned to the bed.
     * 
     * @return the patient assigned to the bed
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient assigned to the bed.
     * 
     * @param patient the new patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Constructs a new HospitalBed with the specified hospital, availability, patient, and bed number.
     * 
     * @param hospital the hospital to which the bed belongs
     * @param isAvailable the availability status of the bed
     * @param patient the patient assigned to the bed
     * @param bedNumber the bed number
     */
    public HospitalBed(Hospital hospital, boolean isAvailable, Patient patient, int bedNumber) {
        super(hospital);
        this.isAvailable = isAvailable;
        this.patient = patient;
        this.bedNumber = bedNumber;
    }

    /**
     * Constructs a new HospitalBed with the specified hospital and bed number.
     * The bed is initially available and has no patient assigned.
     * 
     * @param hospital the hospital to which the bed belongs
     * @param bedNumber the bed number
     */
    public HospitalBed(Hospital hospital, int bedNumber) {
        super(hospital);
        isAvailable = true;
        patient = null;
        this.bedNumber = bedNumber;
    }
}
