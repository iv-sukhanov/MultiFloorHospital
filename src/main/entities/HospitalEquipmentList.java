package main.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HospitalEquipmentList extends Hospital {
    private static Map<String, List<HospitalEquipment>> equipmentMap = new HashMap<>();
    
    public HospitalEquipmentList(Hospital hospital) {
        super(hospital);
    }

    public HospitalEquipmentList(String name, String address, String phoneNumber, String email) {
        super(name, address, phoneNumber, email);
    }

    public int size() {
        return equipmentMap.size();
    }

    public void add(HospitalEquipment h) {
        String name = h.getName();
        
        if (!equipmentMap.containsKey(name)) {
            equipmentMap.put(name, new LinkedList<>());
        }
        equipmentMap.get(name).add(h);
    }

    public List<String> getNames() {
        return new LinkedList<>(equipmentMap.keySet());
    }

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

    public boolean containsKey(String name) {
        return equipmentMap.containsKey(name);
    }

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
