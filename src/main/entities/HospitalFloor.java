package main.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a floor in a hospital.
 */
public class HospitalFloor extends Hospital implements HospitalProperties {
    private int floorNumber;
    private List<HospitalRoom> rooms;

    /**
     * Returns the floor number.
     *
     * @return the floor number
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Sets the floor number.
     *
     * @param floorNumber the new floor number
     */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
     * Returns the list of rooms on the floor.
     *
     * @return the list of rooms
     */
    public List<HospitalRoom> getRooms() {
        return rooms;
    }

    /**
     * Sets the list of rooms on the floor.
     *
     * @param rooms the new list of rooms
     */
    public void setRooms(List<HospitalRoom> rooms) {
        this.rooms = rooms;
    }

    /**
     * Constructs a HospitalFloor object.
     *
     * @param hospital the hospital associated with the floor
     * @param floorNumber the floor number
     * @param numberOfRooms the number of rooms on the floor
     */
    public HospitalFloor(Hospital hospital, int floorNumber, int numberOfRooms) {
        super(hospital);
        this.floorNumber = floorNumber;
        this.rooms = new LinkedList<>();
        int lengthOfRoomNumber = (int)(Math.log10(NUMBER_OF_ROOMS_PER_FLOOR) + 1);
        for (int i = 0; i < numberOfRooms; i++) {
            int currentLength = (int)(Math.log10(i + 1) + 1);
            rooms.add(new HospitalRoom(hospital, floorNumber + "0".repeat(lengthOfRoomNumber - currentLength) + (i + 1), NUMBER_OF_BEDS_PER_ROOM));
        }
    }

    /**
     * Returns the room with the specified room number.
     *
     * @param roomNumber the room number
     * @return the room
     */
    public HospitalRoom getRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }

    /**
     * Returns the names of all rooms on the floor.
     *
     * @return the list of room names
     */
    public List<String> getRoomNames() {
        List<String> roomNames = new LinkedList<>();
        for (HospitalRoom room : rooms) {
            roomNames.add("Room " + room.getRoomNumber());
        }
        return roomNames;
    }

    /**
     * Returns the names of all available rooms on the floor.
     *
     * @return the list of available room names
     */
    public List<String> getAvailableRoomNames() {
        List<String> roomNames = new LinkedList<>();
        for (HospitalRoom room : rooms) {
            if (room.getAvailableBeds().length == 0) {
                continue;
            }
            roomNames.add("Room " + room.getRoomNumber());
        }
        return roomNames;
    }
}
