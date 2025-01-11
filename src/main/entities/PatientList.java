package main.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a list of patients in a hospital.
 */
public class PatientList implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Patient> patients = new LinkedList<>();

    /**
     * Adds a patient to the list.
     *
     * @param patient the patient to add
     */
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Returns the number of patients in the list.
     *
     * @return the number of patients
     */
    public int size() {
        return patients.size();
    }

    /**
     * Returns the names of all patients in the list.
     *
     * @return an array of patient names
     */
    public String[] getPatientNames() {
        String[] patientNames = new String[size()];
        for (int i = 0; i < patients.size(); i++) {
            patientNames[i] = patients.get(i).getName();
        }
        return patientNames;
    }

    /**
     * Returns the patient at the specified index.
     *
     * @param index the index of the patient
     * @return the patient at the specified index, or null if the index is out of range
     */
    public Patient getPatient(int index) {
        if (index < 0 || index >= patients.size()) {
            return null;
        }
        return patients.get(index);
    }

    /**
     * Removes the patient at the specified index.
     *
     * @param index the index of the patient to remove
     * @return true if the patient was removed, false otherwise
     */
    public boolean removePatient(int index) {
        if (index < 0 || index >= patients.size()) {
            return false;
        }
        Patient patient = patients.remove(index);
        if (patient != null) {
            patient.clean();
            return true;
        }
        return false;
    }

    /**
     * Returns the details of all patients in the list.
     *
     * @return a 2D array containing the details of the patients
     */
    public String[][] getPatientsDetails() {
        String[][] patientsDetails = new String[size()][14];
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            patientsDetails[i][0] = patient.getName();
            patientsDetails[i][1] = String.valueOf(patient.getAge());
            patientsDetails[i][2] = patient.getDateOfBirth().toString();
            patientsDetails[i][3] = patient.getIdNumber();
            patientsDetails[i][4] = patient.getPhoneNumber();
            patientsDetails[i][5] = patient.getEmail() == null ? "N/A" : patient.getEmail();
            patientsDetails[i][6] = patient.getGender() ? "Male" : "Female";
            patientsDetails[i][7] = patient.isOwnsCar() ? "Yes" : "No";
            patientsDetails[i][8] = patient.getCarNumber() == null ? "N/A" : patient.getCarNumber();
            patientsDetails[i][9] = patient.getDiagnosis() == null ? "N/A" : patient.getDiagnosis();
            patientsDetails[i][10] = patient.getAssignedDoctor() == null ? "N/A" : patient.getAssignedDoctor().getName();
            patientsDetails[i][11] = patient.getAssignedRoom() == null ? "N/A" : patient.getAssignedRoom().getRoomNumber();
            patientsDetails[i][12] = patient.getAssignedBed() == null ? "N/A" : String.valueOf(patient.getAssignedBed().getBedNumber());
            patientsDetails[i][13] = patient.getAssignedEquipment() == null ? "N/A" : patient.getAssignedEquipment().getName();
        }
        return patientsDetails;
    }
}
