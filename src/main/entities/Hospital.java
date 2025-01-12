package main.entities;

import java.io.Serializable;

/**
 * The Hospital class represents a hospital entity.
 * It includes details about the hospital's name, address, phone number, and email.
 */
public abstract class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hospitalName;
    private String hospitalAddress;
    private String hospitalPhoneNumber;
    private String hospitalEmail;
     
    /**
     * Gets the name of the hospital.
     * 
     * @return the name of the hospital
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * Gets the address of the hospital.
     * 
     * @return the address of the hospital
     */
    public String getHospitalAddress() {
        return hospitalAddress;
    }

    /**
     * Gets the phone number of the hospital.
     * 
     * @return the phone number of the hospital
     */
    public String getHospitalPhoneNumber() {
        return hospitalPhoneNumber;
    }

    /**
     * Gets the email of the hospital.
     * 
     * @return the email of the hospital
     */
    public String getHospitalEmail() {
        return hospitalEmail;
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
        this.hospitalName = name;
        this.hospitalAddress = address;
        this.hospitalPhoneNumber = phoneNumber;
        this.hospitalEmail = email;
    }

    /**
     * Constructs a new Hospital by copying the details from another hospital.
     * 
     * @param hospital the hospital to copy details from
     */
    public Hospital(Hospital hospital) {
        this.hospitalName = hospital.getHospitalName();
        this.hospitalAddress = hospital.getHospitalAddress();
        this.hospitalPhoneNumber = hospital.getHospitalPhoneNumber();
        this.hospitalEmail = hospital.getHospitalEmail();
    }
}
