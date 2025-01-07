package main.entities;

import java.util.LinkedList;
import java.util.List;

public class HospitalFloor extends Hospital implements RoomProperties {
    private int floorNumber;
    private List<HospitalRoom> rooms;

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<HospitalRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HospitalRoom> rooms) {
        this.rooms = rooms;
    }

    public HospitalFloor(Hospital hospital, int floorNumber, int NumberOfRooms) {
        super(hospital);
        this.floorNumber = floorNumber;
        this.rooms = new LinkedList<>();
        for (int i = 0; i < NumberOfRooms; i++) {
            rooms.add(new HospitalRoom(hospital, floorNumber + "" + (i + 1), NUMBER_OF_BEDS));
        }
    }
}
