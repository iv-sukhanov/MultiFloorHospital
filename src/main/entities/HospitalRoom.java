package main.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a room in a hospital.
 */
public class HospitalRoom extends Hospital {
    private String roomNumber;
    private final List<HospitalBed> beds;

    /**
     * Returns the room number.
     *
     * @return the room number
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number.
     *
     * @param roomNumber the new room number
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Constructs a HospitalRoom object.
     *
     * @param hospital the hospital associated with the room
     * @param roomNumber the room number
     * @param numberOfBeds the number of beds in the room
     */
    HospitalRoom(Hospital hospital, String roomNumber, int numberOfBeds) {
        super(hospital);
        this.roomNumber = roomNumber;
        this.beds = new ArrayList<>(numberOfBeds);
        for (int i = 0; i < numberOfBeds; i++) {
            beds.add(new HospitalBed(hospital, i + 1));
        }
    }

    /**
     * Returns information about all beds in the room.
     *
     * @return a string containing information about all beds
     */
    public String getBedsInfo() {
        StringBuilder bedsInfo = new StringBuilder();
        for (HospitalBed bed : beds) {
            bedsInfo
                .append("Bed ")
                .append(bed.getBedNumber())
                .append(" is ")
                .append(bed.isAvailable() ? "available\n" : "occupied by " + bed.getPatient().getName() + "\n");
        }
        return bedsInfo.toString();
    }

    /**
     * Returns the available beds in the room.
     *
     * @return an array of available bed numbers
     */
    public String[] getAvailableBeds() {
        List<String> availableBeds = new LinkedList<>();
        for (HospitalBed bed : beds) {
            if (bed.isAvailable()) {
                availableBeds.add("Bed " + bed.getBedNumber());
            }
        }
        return availableBeds.toArray(new String[0]);
    }

    /**
     * Returns the bed with the specified bed number.
     *
     * @param bedNumber the bed number
     * @return the bed with the specified bed number
     */
    public HospitalBed getBed(int bedNumber) {
        return beds.get(bedNumber);
    }

    public HospitalBed getAvailableBed(int bedIndex) {
        
        int availableBedIndex = 0;
        for (int i = 0; i < beds.size(); i++) {
            if (beds.get(i).isAvailable()) {
                if (availableBedIndex == bedIndex) {
                    return beds.get(i);
                }
                availableBedIndex++;
            }
        }

        return null;
    }
}
