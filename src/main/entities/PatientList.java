package main.entities;

import java.util.LinkedList;
import java.util.List;

public class PatientList {
    private final List<Patient> patients = new LinkedList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public int size() {
        return patients.size();
    }

    public String[] getPatientNames() {
        String[] patientNames = new String[size()];
        for (int i = 0; i < patients.size(); i++) {
            patientNames[i] = patients.get(i).getName();
        }
        return patientNames;
    }

    public Patient getPatient(int index) {
        return patients.get(index);
    }

    public String[][] getPatientsDetails() {
        String[][] patientsDetails = new String[size()][14];
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            patientsDetails[i][0] = patient.getName();
            patientsDetails[i][1] = String.valueOf(patient.getAge());
            patientsDetails[i][2] = patient.getDateOfBirth().toString();
            patientsDetails[i][3] = patient.getIdNumber();
            patientsDetails[i][4] = patient.getPhoneNumber();
            patientsDetails[i][5] = patient.getEmail();
            patientsDetails[i][6] = patient.isGender() ? "Male" : "Female";
            patientsDetails[i][7] = patient.isOwnsCar() ? "Yes" : "No";
            patientsDetails[i][8] = patient.getCarNumber() == null ? "N/A" : patient.getCarNumber();
            patientsDetails[i][9] = patient.getDiagnosis();
            patientsDetails[i][10] = patient.getAssignedDoctor() == null ? "N/A" : patient.getAssignedDoctor().getName();
            patientsDetails[i][11] = patient.getAssignedRoom() == null ? "N/A" : patient.getAssignedRoom().getRoomNumber();
            patientsDetails[i][12] = patient.getAssignedBed() == null ? "N/A" : String.valueOf(patient.getAssignedBed().getBedNumber());
            patientsDetails[i][13] = patient.getAssignedEquipment() == null ? "N/A" : patient.getAssignedEquipment().getName();
        }

        
        return patientsDetails;
    }
}
