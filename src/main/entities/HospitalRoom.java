package main.entities;

import java.util.LinkedList;
import java.util.List;

public class HospitalRoom extends Hospital {
    private String roomNumber;
    private final List<HospitalBed> beds;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    HospitalRoom(Hospital hospital, String roomNumber, int NumberOfBeds) {
        super(hospital);
        this.roomNumber = roomNumber;
        this.beds = new LinkedList<>();
        for (int i = 0; i < NumberOfBeds; i++) {
            beds.add(new HospitalBed(hospital, i + 1));
        }
    }

    public String getBedsInfo() {
        StringBuilder bedsInfo = new StringBuilder();
        for (HospitalBed bed : beds) {
            bedsInfo.
            append("Bed ").
            append(bed.getBedNumber()).
            append(" is ").
            append(
                bed.isAvailable() ? 
                "available\n" : 
                "occupied by " + bed.getPatient().getName() + "\n"
            );
        }
        return bedsInfo.toString();
    }
}
