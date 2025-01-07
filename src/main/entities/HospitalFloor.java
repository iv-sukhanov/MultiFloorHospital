package main.entities;

import java.util.LinkedList;
import java.util.List;

public class HospitalFloor extends Hospital implements HospitalProperties {
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
        int lengthOfRoomNumber = (int)(Math.log10(NUMBER_OF_ROOMS_PER_FLOOR) + 1);
        for (int i = 0; i < NumberOfRooms; i++) {
            int currentLength = (int)(Math.log10(i + 1) + 1);

            rooms.add(new HospitalRoom(hospital, floorNumber + "0".repeat(lengthOfRoomNumber - currentLength) + (i + 1), NUMBER_OF_BEDS_PER_ROOM));
        }
    }

    public HospitalRoom getRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }

    public List<String> getRoomNames() {
        List<String> roomNames = new LinkedList<>();
        for (HospitalRoom room : rooms) {
            roomNames.add("Room " + room.getRoomNumber());
        }
        return roomNames;
    }
}
