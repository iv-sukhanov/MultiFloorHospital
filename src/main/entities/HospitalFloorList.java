package main.entities;

import java.util.ArrayList;
import java.util.List;

public class HospitalFloorList extends Hospital implements HospitalProperties {
    private final List<HospitalFloor> hospitalFloors = new ArrayList<>();

    public HospitalFloorList(Hospital hospital, int numberOfFloors) {
        super(hospital);
        for (int i = 0; i < numberOfFloors; i++) {
            hospitalFloors.add(new HospitalFloor(this, i + 1, NUMBER_OF_ROOMS_PER_FLOOR));
        }
    }

    public int size() {
        return hospitalFloors.size();
    }

    public HospitalFloor get(int index) {
        return hospitalFloors.get(index);
    }
}
