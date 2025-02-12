package com.natursalas.natursalassystem.model.dto;

public class UserDTO {
    private int idUser;
    private String userName;
    private String password;
    private String role; // Nuevo campo
    private String idLocation;

    public UserDTO() {
    }

    public UserDTO(int idUser, String userName, String password, String role, String idLocation) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.idLocation = idLocation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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