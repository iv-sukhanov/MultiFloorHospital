package main.entities;

public class HospitalBed extends Hospital {
    private boolean isAvailable;
    private Patient patient;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public HospitalBed(Hospital hospital, boolean isAvailable, Patient patient) {
        super(hospital);

        this.isAvailable = isAvailable;
        this.patient = patient;
    }

    public HospitalBed(Hospital hospital) {
        super(hospital);
        isAvailable = true;
        patient = null;
    }
}
