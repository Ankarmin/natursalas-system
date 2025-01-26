package com.natursalas.natursalassystem.model.dto;

import java.sql.Date;

public class PatientDTO {
    private String idPatient;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private Date dateOfConsultation;
    private Date dateOfBirth;
    private String district;
    private String idLocation;

    public PatientDTO() {
    }

    public PatientDTO(String idPatient, String firstName, String lastName, int age, String phoneNumber, Date dateOfConsultation, Date dateOfBirth, String district, String idLocation) {
        this.idPatient = idPatient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.dateOfConsultation = dateOfConsultation;
        this.dateOfBirth = dateOfBirth;
        this.district = district;
        this.idLocation = idLocation;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
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

    public Date getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }
}