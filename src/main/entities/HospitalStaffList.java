package main.entities;

import java.util.LinkedList;
import java.util.List;

public class HospitalStaffList extends Hospital {
    private final List<HospitalStaff> staffList = new LinkedList<>();

    public HospitalStaffList(Hospital hospital) {
        super(hospital);
    }

    public HospitalStaffList(String name, String address, String phoneNumber, String email) {
        super(name, address, phoneNumber, email);
    }

    public int size() {
        return staffList.size();
    }

    public void add(HospitalStaff h) {
        if (h == null) {
            return;
        }
        staffList.add(h);
    }

    public boolean remove(int index) {
        if (index < 0 || index >= staffList.size()) {
            return false;
        }
        staffList.remove(index);
        return true;
    }

    public String[][] getDetails() {
        String[][] details = new String[staffList.size()][12];
        for (int i = 0; i < staffList.size(); i++) {
            HospitalStaff h = staffList.get(i);
            details[i][0] = h.getName();
            details[i][1] = String.valueOf(h.getAge());
            details[i][2] = h.getDateOfBirth().toString();
            details[i][3] = h.getIdNumber();
            details[i][4] = h.getPhoneNumber();
            details[i][5] = h.getEmail();
            details[i][6] = h.isGender() ? "Male" : "Female";
            details[i][7] = h.isOwnsCar() ? "Yes" : "No";
            details[i][8] = h.getCarNumber() == null ? "N/A" : h.getCarNumber();
            details[i][9] = h.getPosition();
            details[i][10] = h.isAvailable() ? "Yes" : "No";
            details[i][11] = h.getPatient() == null ? "N/A" : h.getPatient().getName();
        }
        return details;
    }

    public HospitalStaff get(int index) {
        return staffList.get(index);
    }
}
