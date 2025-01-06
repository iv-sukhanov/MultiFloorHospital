package main.entities;

import java.time.LocalDate;

public abstract class Person {
    private String name;
    private int age;
    private LocalDate dateOfBirth;
    private String idNumber;
    private String phoneNumber;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null");
        }

        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {

        if (!idNumber.matches("\\d+")) {
            throw new IllegalArgumentException("ID number must contain only digits");
        }

        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        if (!phoneNumber.matches("^(\\+\\d{1,3} )?(\\(\\d{3}\\)|\\d{3})[- ]?\\d{3}[- ]?\\d{4}$")) {
            throw new IllegalArgumentException("Wrong phone number format");
        } 

        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Wrong email format");
        }

        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isOwnsCar() {
        return ownsCar;
    }

    public void setOwnsCar(boolean ownsCar) {
        this.ownsCar = ownsCar;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {

        if (!carNumber.matches("^[A-Z]{3}-\\d{3}$")) {
            throw new IllegalArgumentException("Wrong car number format, it should be XXX-000");
        }

        this.carNumber = carNumber;
    }

    private String email;
    private boolean gender;
    private boolean ownsCar;
    private String carNumber;

    public Person(String name, LocalDate dateOfBirth, String idNumber, String phoneNumber, String email, boolean isMale, boolean ownsCar, String carNumber) {
        setName(name);
        // setAge(age); TODO
        setDateOfBirth(dateOfBirth);
        setIdNumber(idNumber);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setGender(isMale);
        setOwnsCar(ownsCar);
        setCarNumber(carNumber);
    }

}
