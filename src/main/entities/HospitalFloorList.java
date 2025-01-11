package main.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of floors in a hospital.
 */
public class HospitalFloorList extends Hospital implements HospitalProperties {
    private final List<HospitalFloor> hospitalFloors = new ArrayList<>();

    /**
     * Constructs a HospitalFloorList object.
     *
     * @param hospital the hospital associated with the floors
     * @param numberOfFloors the number of floors in the hospital
     */
    public HospitalFloorList(Hospital hospital, int numberOfFloors) {
        super(hospital);
        for (int i = 0; i < numberOfFloors; i++) {
            hospitalFloors.add(new HospitalFloor(this, i + 1, NUMBER_OF_ROOMS_PER_FLOOR));
        }
    }

    /**
     * Returns the number of floors in the hospital.
     *
     * @return the number of floors
     */
    public int size() {
        return hospitalFloors.size();
    }

    /**
     * Returns the floor at the specified index.
     *
     * @param index the index of the floor
     * @return the floor at the specified index
     */
    public HospitalFloor get(int index) {
        return hospitalFloors.get(index);
    }
}
