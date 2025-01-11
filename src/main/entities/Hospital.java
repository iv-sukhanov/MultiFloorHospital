package main.entities;

import java.io.Serializable;

public abstract class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
     
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Hospital(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Hospital(Hospital hospital) {
        this.name = hospital.getName();
        this.address = hospital.getAddress();
        this.phoneNumber = hospital.getPhoneNumber();
        this.email = hospital.getEmail();
    }

    //FIXME public Hospital() {}
}
