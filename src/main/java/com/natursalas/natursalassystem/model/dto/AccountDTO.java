package com.natursalas.natursalassystem.model.dto;

public class AccountDTO {
    private String idLocation;
    private String address;
    private String email;
    private String password;

    public AccountDTO() {
    }

    public AccountDTO(String idLocation, String address, String email, String password) {
        this.idLocation = idLocation;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
