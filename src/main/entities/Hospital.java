package main.entities;

import java.io.Serializable;

/**
 * The Hospital class represents a hospital entity.
 * It includes details about the hospital's name, address, phone number, and email.
 */
public abstract class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
     
    /**
     * Gets the name of the hospital.
     * 
     * @return the name of the hospital
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the address of the hospital.
     * 
     * @return the address of the hospital
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the phone number of the hospital.
     * 
     * @return the phone number of the hospital
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the email of the hospital.
     * 
     * @return the email of the hospital
     */
    public String getEmail() {
        return email;
    }

    /**
     * Constructs a new Hospital with the specified name, address, phone number, and email.
     * 
     * @param name the name of the hospital
     * @param address the address of the hospital
     * @param phoneNumber the phone number of the hospital
     * @param email the email of the hospital
     */
    public Hospital(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Constructs a new Hospital by copying the details from another hospital.
     * 
     * @param hospital the hospital to copy details from
     */
    public Hospital(Hospital hospital) {
        this.name = hospital.getName();
        this.address = hospital.getAddress();
        this.phoneNumber = hospital.getPhoneNumber();
        this.email = hospital.getEmail();
    }
}
