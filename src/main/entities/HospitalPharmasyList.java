package main.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents a list of pharmacy items in a hospital.
 */
public class HospitalPharmasyList extends Hospital {
    
    private final Map<String, List<HospitalPharmasy>> pharmasyMap = new HashMap<>();

    /**
     * Constructs a HospitalPharmasyList object.
     *
     * @param hospital the hospital associated with the pharmacy list
     */
    public HospitalPharmasyList(Hospital hospital) {
        super(hospital);
    }

    /**
     * Adds a pharmacy item to the list.
     *
     * @param p the pharmacy item to add
     */
    public void addPharmasy(HospitalPharmasy p) {
        if (p == null) {
            return;
        }
        String name = p.getName();
        if (!pharmasyMap.containsKey(name)) {
            pharmasyMap.put(name, new LinkedList<>());
        }
        pharmasyMap.get(name).add(p);
    }

    /**
     * Returns the names of all pharmacy items in the list.
     *
     * @return the list of pharmacy item names
     */
    public List<String> getPharmasyNames() {
        return new LinkedList<>(pharmasyMap.keySet());
    }

    /**
     * Removes a pharmacy item by name.
     *
     * @param name the name of the pharmacy item to remove
     * @return true if the item was removed, false otherwise
     */
    public boolean remove(String name) {
        if (!pharmasyMap.containsKey(name)) {
            return false;
        }
        List<HospitalPharmasy> currentList = pharmasyMap.get(name);
        if (currentList.remove(0) != null) {
            if (currentList.isEmpty()) {
                pharmasyMap.remove(name);
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the details of all pharmacy items with the specified name.
     *
     * @param name the name of the pharmacy items
     * @return a 2D array containing the details of the pharmacy items
     */
    public String[][] getPharmasyDetails(String name) {
        if (!pharmasyMap.containsKey(name)) {
            return null;
        }
        List<HospitalPharmasy> currentList = pharmasyMap.get(name);
        String[][] details = new String[currentList.size()][4];
        for (int i = 0; i < currentList.size(); i++) {
            HospitalPharmasy currentPharmasy = currentList.get(i);
            details[i][0] = String.valueOf(i + 1);
            details[i][1] = currentPharmasy.getName();
            details[i][2] = currentPharmasy.getPrice() == 0 ? "free" : String.valueOf(currentPharmasy.getPrice());
            details[i][3] = currentPharmasy.getExpirationDate().toString();
        }
        return details;
    }
}