package main.entities;

import java.time.LocalDate;

/**
 * Represents equipment in a hospital.
 */
public class HospitalEquipment extends Hospital{
    
    private static final long serialVersionUID = 1L;

    private static int idCounter = 0;

    private String name;
    private String manufacturer;
    private int id;
    private boolean isAvailable;
    private LocalDate lastMaintenanceDate;

    /**
     * Gets the name of the equipment.
     * @return the name of the equipment.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the equipment.
     * @param name the name to set.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
    }

    /**
     * Gets the manufacturer of the equipment.
     * @return the manufacturer of the equipment.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer of the equipment.
     * @param manufacturer the manufacturer to set.
     * @throws IllegalArgumentException if the manufacturer is null or empty.
     */
    public void setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.isEmpty()) {
            throw new IllegalArgumentException("Manufacturer cannot be null or empty");
        }
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the ID of the equipment.
     * @return the ID of the equipment.
     */
    public int getId() {
        return id;
    }

    /**
     * Checks if the equipment is available.
     * @return true if the equipment is available, false otherwise.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of the equipment.
     * @param isAvailable the availability to set.
     */
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * Gets the last maintenance date of the equipment.
     * @return the last maintenance date.
     */
    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    /**
     * Sets the last maintenance date of the equipment.
     * @param lastMaintenanceDate the last maintenance date to set.
     */
    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    /**
     * Constructs a new HospitalEquipment with the specified details.
     * @param name the name of the equipment.
     * @param manufacturer the manufacturer of the equipment.
     * @param isAvailable the availability of the equipment.
     * @param lastMaintenanceDate the last maintenance date of the equipment.
     * @param hospital the hospital to which the equipment belongs.
     */
    public HospitalEquipment(String name, String manufacturer, boolean isAvailable, LocalDate lastMaintenanceDate, Hospital hospital) {
        super(hospital);

        this.name = name;
        this.manufacturer = manufacturer;
        this.id = idCounter++;
        this.isAvailable = isAvailable;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    /**
     * Constructs a new HospitalEquipment with the specified name, manufacturer, and hospital.
     * @param name the name of the equipment.
     * @param manufacturer the manufacturer of the equipment.
     * @param hospital the hospital to which the equipment belongs.
     */
    public HospitalEquipment(String name, String manufacturer, Hospital hospital) {
        this(name, manufacturer, true, LocalDate.now(), hospital);
    }

    /**
     * Constructs a new HospitalEquipment with the specified name and hospital.
     * @param name the name of the equipment.
     * @param hospital the hospital to which the equipment belongs.
     */
    public HospitalEquipment(String name, Hospital hospital) {
        this(name, "Unknown", hospital);
    }
}
