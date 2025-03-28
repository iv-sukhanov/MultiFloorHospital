package main.entities;

/**
 * The HospitalProperties interface defines a set of constants representing
 * various properties of a hospital, including the number of beds per room,
 * the number of rooms per floor, the number of floors, and the hospital's
 * name, address, phone number, and email.
 */
public interface HospitalProperties {
    int NUMBER_OF_BEDS_PER_ROOM = 5;
    int NUMBER_OF_ROOMS_PER_FLOOR = 99;
    int NUMBER_OF_FLOORS = 3;

    String HOSPITAL_NAME = "MultiFloorHospital";
    String HOSPITAL_ADDRESS = "Nicosia";
    String HOSPITAL_PHONE = "+357 22 123456";
    String HOSPITAL_EMAIL = "hospital@health.com";
}
