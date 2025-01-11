package main.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents a list of hospital equipment.
 */
public class HospitalEquipmentList extends Hospital {

    private static final long serialVersionUID = 1L;

    private final Map<String, List<HospitalEquipment>> equipmentMap = new HashMap<>();
    
    /**
     * Constructs a new HospitalEquipmentList with the specified hospital.
     * @param hospital the hospital to which the equipment list belongs.
     */
    public HospitalEquipmentList(Hospital hospital) {
        super(hospital);
    }

    /**
     * Constructs a new HospitalEquipmentList with the specified details.
     * @param name the name of the hospital.
     * @param address the address of the hospital.
     * @param phoneNumber the phone number of the hospital.
     * @param email the email of the hospital.
     */
    public HospitalEquipmentList(String name, String address, String phoneNumber, String email) {
        super(name, address, phoneNumber, email);
    }

    /**
     * Gets the number of different types of equipment in the list.
     * @return the number of different types of equipment.
     */
    public int size() {
        return equipmentMap.size();
    }

    /**
     * Gets the first available equipment with the specified name.
     * @param name the name of the equipment.
     * @return the first available equipment, or null if none is available.
     */
    public HospitalEquipment getFirstUnused(String name) {
        if (!equipmentMap.containsKey(name)) {
            return null;
        }

        List<HospitalEquipment> equipmentList = equipmentMap.get(name);
        for (HospitalEquipment equipment : equipmentList) {
            if (equipment.isAvailable()) {
                return equipment;
            }
        }
        return null;

    }

    /**
     * Adds a new equipment to the list.
     * @param h the equipment to add.
     */
    public void add(HospitalEquipment h) {
        
        if (h == null) {
            return;
        }
        
        String name = h.getName();
        
        if (!equipmentMap.containsKey(name)) {
            equipmentMap.put(name, new LinkedList<>());
        }
        equipmentMap.get(name).add(h);
    }

    /**
     * Gets the names of all types of equipment in the list.
     * @return a list of equipment names.
     */
    public List<String> getNames() {
        return new LinkedList<>(equipmentMap.keySet());
    }

    /**
     * Gets the details of all equipment with the specified name.
     * @param name the name of the equipment.
     * @return a 2D array containing the details of the equipment.
     */
    public String[][] getDetails(String name) {
        List<HospitalEquipment> equipmentList = equipmentMap.get(name);
        String[][] details = new String[equipmentList.size()][5];

        for (int i = 0; i < equipmentList.size(); i++) {
            HospitalEquipment equipment = equipmentList.get(i);
            details[i][0] = String.valueOf(i + 1);
            details[i][1] = equipment.getName();
            details[i][2] = equipment.getManufacturer();
            details[i][3] = equipment.getLastMaintenanceDate().toString();
            details[i][4] = String.valueOf(equipment.isAvailable());
        }
        
        return details;        
    }

    /**
     * Checks if the list contains equipment with the specified name.
     * @param name the name of the equipment.
     * @return true if the list contains equipment with the specified name, false otherwise.
     */
    public boolean containsKey(String name) {
        return equipmentMap.containsKey(name);
    }

    /**
     * Removes the equipment at the specified index with the specified name.
     * @param name the name of the equipment.
     * @param index the index of the equipment to remove.
     * @return true if the equipment was removed, false otherwise.
     */
    public boolean remove(String name, int index) {
        if (!equipmentMap.containsKey(name)) {
            return false;
        }
        List<HospitalEquipment> currentList = equipmentMap.get(name);

        if (currentList.remove(index) != null) {
            if (currentList.isEmpty()) {
                equipmentMap.remove(name);
            }
            return true;
        }
        return false;
    }
}
