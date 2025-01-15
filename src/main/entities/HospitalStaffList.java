package main.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a list of hospital staff members.
 */
public class HospitalStaffList extends Hospital {
    
    private final List<HospitalStaff> staffList = new LinkedList<>();

    /**
     * Constructs a HospitalStaffList object.
     *
     * @param hospital the hospital associated with the staff list
     */
    public HospitalStaffList(Hospital hospital) {
        super(hospital);
    }

    /**
     * Returns the number of staff members in the list.
     *
     * @return the number of staff members
     */
    public int size() {
        return staffList.size();
    }

    /**
     * Adds a staff member to the list.
     *
     * @param h the staff member to add
     */
    public void add(HospitalStaff h) {
        if (h == null) {
            return;
        }
        staffList.add(h);
    }

    /**
     * Removes a staff member at the specified index.
     *
     * @param index the index of the staff member to remove
     * @return true if the staff member was removed, false otherwise
     */
    public boolean remove(int index) {
        if (index < 0 || index >= staffList.size()) {
            return false;
        }
        HospitalStaff removedStaff = staffList.remove(index);
        if (removedStaff != null) {
            removedStaff.clean();
        }
        return true;
    }

    /**
     * Returns the details of all staff members.
     *
     * @return a 2D array containing the details of the staff members
     */
    public String[][] getDetails() {
        String[][] details = new String[staffList.size()][12];
        for (int i = 0; i < staffList.size(); i++) {
            HospitalStaff h = staffList.get(i);
            details[i][0] = h.getName();
            details[i][1] = String.valueOf(h.getAge());
            details[i][2] = h.getDateOfBirth().toString();
            details[i][3] = h.getIdNumber();
            details[i][4] = h.getPhoneNumber();
            details[i][5] = h.getEmail() == null ? "N/A" : h.getEmail();
            details[i][6] = h.getGender() ? "Male" : "Female";
            details[i][7] = h.isOwnsCar() ? "Yes" : "No";
            details[i][8] = h.getCarNumber() == null ? "N/A" : h.getCarNumber();
            details[i][9] = h.getPosition();
            details[i][10] = h.isAvailable() ? "Yes" : "No";
            details[i][11] = h.getPatient() == null ? "N/A" : h.getPatient().getName();
        }
        return details;
    }

    /**
     * Returns the staff member at the specified index.
     *
     * @param index the index of the staff member
     * @return the staff member at the specified index, or null if the index is out of range
     */
    public HospitalStaff get(int index) {
        if (index < 0 || index >= staffList.size()) {
            return null;
        }
        return staffList.get(index);
    }

    public HospitalStaff getAvailable(int index) {
        if (index < 0 || index >= staffList.size()) {
            return null;
        }

        for (HospitalStaff staff : staffList) {
            if (staff.isAvailable()) {
                if (index == 0) {
                    return staff;
                }
                index--;
            }
        }

        return staffList.get(index);
    }

    /**
     * Returns the names of all staff members in the list.
     *
     * @return an array of staff member names
     */
    public String[] getStaffNames() {
        String[] staffNames = new String[staffList.size()];
        for (int i = 0; i < staffList.size(); i++) {
            staffNames[i] = staffList.get(i).getName();
        }
        return staffNames;
    }

    public String[] getAvailableStaffNames() {
        List<String> availableStaffNames = new LinkedList<>();
        for (HospitalStaff staff : staffList) {
            if (staff.isAvailable()) {
                availableStaffNames.add(staff.getName());
            }
        }
        return availableStaffNames.toArray(new String[0]);
    }
}
