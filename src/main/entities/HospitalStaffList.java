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

    public HospitalStaff get(int index) {
        return staffList.get(index);
    }
}
