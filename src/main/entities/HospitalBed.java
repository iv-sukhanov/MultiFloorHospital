package main.entities;

public class HospitalBed extends Hospital {
    private int bedNumber;
    private boolean isAvailable;
    private Patient patient;

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

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

    public HospitalBed(Hospital hospital, boolean isAvailable, Patient patient, int bedNumber) {
        super(hospital);

        this.isAvailable = isAvailable;
        this.patient = patient;
        this.bedNumber = bedNumber;
    }

    public HospitalBed(Hospital hospital, int bedNumber) {
        super(hospital);
        isAvailable = true;
        patient = null;
        this.bedNumber = bedNumber;
    }
}
