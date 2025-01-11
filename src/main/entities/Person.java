package main.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/**
 * Represents a person with basic personal information.
 */
public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private LocalDate dateOfBirth;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private boolean gender;
    private boolean ownsCar;
    private String carNumber;

    /**
     * Returns the name of the person.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the new name
     * @throws IllegalArgumentException if the name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    /**
     * Returns the age of the person.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person based on the date of birth.
     */
    public void setAge() {
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * Returns the date of birth of the person.
     *
     * @return the date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the person.
     *
     * @param dateOfBirth the new date of birth
     * @throws IllegalArgumentException if the date of birth is null or in the future
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the ID number of the person.
     *
     * @return the ID number
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Sets the ID number of the person.
     *
     * @param idNumber the new ID number
     * @throws IllegalArgumentException if the ID number contains non-digit characters
     */
    public void setIdNumber(String idNumber) {
        if (!idNumber.matches("\\d+")) {
            throw new IllegalArgumentException("ID number must contain only digits");
        }
        this.idNumber = idNumber;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber the new phone number
     * @throws IllegalArgumentException if the phone number format is incorrect
     */
    public void setPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("^(\\+\\d{1,3} )? ?\\(?\\d{1,4}\\)?[- ]?\\d{1,4}[- ]?\\d{1,4}$")) {
            throw new IllegalArgumentException("Wrong phone number format, \nfor example it could be +XXX XXXX XX XX");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the email of the person.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the person.
     *
     * @param email the new email
     * @throws IllegalArgumentException if the email format is incorrect
     */
    public void setEmail(String email) {
        if (email != null && !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Wrong email format, \ntry something like xxxxx@xxxx.xxx\nor leave it empty");
        }
        this.email = email;
    }

    /**
     * Returns the gender of the person.
     *
     * @return true if male, false if female
     */
    public boolean getGender() {
        return gender;
    }

    /**
     * Sets the gender of the person.
     *
     * @param gender the new gender
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * Returns whether the person owns a car.
     *
     * @return true if the person owns a car, false otherwise
     */
    public boolean isOwnsCar() {
        return ownsCar;
    }

    /**
     * Sets whether the person owns a car.
     *
     * @param ownsCar the new car ownership status
     */
    public void setOwnsCar(boolean ownsCar) {
        this.ownsCar = ownsCar;
    }

    /**
     * Returns the car number of the person.
     *
     * @return the car number
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * Sets the car number of the person.
     *
     * @param carNumber the new car number
     * @throws IllegalArgumentException if the car number format is incorrect
     */
    public void setCarNumber(String carNumber) {
        if (!(carNumber == null) && !carNumber.matches("^[A-Z]{3}[- ]?\\d{3}$")) {
            throw new IllegalArgumentException("Wrong car number format, it should be XXX-000");
        }
        this.carNumber = carNumber;
    }

    /**
     * Constructs a Person object.
     *
     * @param name the name of the person
     * @param dateOfBirth the date of birth of the person
     * @param idNumber the ID number of the person
     * @param phoneNumber the phone number of the person
     * @param email the email of the person
     * @param isMale the gender of the person
     * @param ownsCar whether the person owns a car
     * @param carNumber the car number of the person
     */
    public Person(String name, LocalDate dateOfBirth, String idNumber, String phoneNumber, String email, boolean isMale, boolean ownsCar, String carNumber) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setAge();
        setIdNumber(idNumber);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setGender(isMale);
        setOwnsCar(ownsCar);
        setCarNumber(carNumber);
    }
}
