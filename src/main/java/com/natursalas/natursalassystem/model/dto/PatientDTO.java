package com.natursalas.natursalassystem.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class PatientDTO {
    private String DNI;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private Timestamp dateOfEntry;
    private Date dateOfBirth;
    private String idLocation;

    public PatientDTO() {
    }

    public PatientDTO(String DNI, String firstName, String lastName, int age, String phoneNumber, Timestamp dateOfEntry, Date dateOfBirth, String idLocation) {
        this.DNI = DNI;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.dateOfEntry = dateOfEntry;
        this.dateOfBirth = dateOfBirth;
        this.idLocation = idLocation;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry() {
        this.dateOfEntry = new Timestamp(System.currentTimeMillis());
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}