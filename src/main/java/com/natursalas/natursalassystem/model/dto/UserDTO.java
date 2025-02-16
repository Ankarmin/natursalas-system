package com.natursalas.natursalassystem.model.dto;

public class UserDTO {
    private String email;
    private String password;
    private String role;
    private String idLocation;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String role, String idLocation) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.idLocation = idLocation;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }
}