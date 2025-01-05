package main.entities;

import java.time.LocalDate;

public class HospitalEquipment {
    
    private static int idCounter = 0;

    private String name;
    private String manufacturer;
    private int id;
    private boolean isAvailable;
    private LocalDate lastMaintenanceDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be null or empty");
        }
        this.manufacturer = manufacturer;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public HospitalEquipment(String name, String manufacturer, boolean isAvailable, LocalDate lastMaintenanceDate) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.id = idCounter++;
        this.isAvailable = isAvailable;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public String toString() {
        return "HospitalEquipment [name=" + name + ", manufacturer=" + manufacturer + ", id=" + id + ", isAvailable="
                + isAvailable + ", lastMaintenanceDate=" + lastMaintenanceDate + "]";
    }

    public HospitalEquipment(String name, String manufacturer) {
        this(name, manufacturer, true, LocalDate.now());
    }

    public HospitalEquipment(String name) {
        this(name, "Unknown");
    }
}
