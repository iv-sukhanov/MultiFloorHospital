package main.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HospitalPharmasyList extends Hospital {
    
    private final Map<String, List<HospitalPharmasy>> pharmasyMap = new HashMap<>();

    public HospitalPharmasyList(Hospital hospital) {
        super(hospital);
    }

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

    public List<String> getPharmasyNames() {
        return new LinkedList<>(pharmasyMap.keySet());
    }

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