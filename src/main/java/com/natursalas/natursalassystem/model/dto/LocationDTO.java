package com.natursalas.natursalassystem.model.dto;

public class LocationDTO {
    private String idLocation;
    private String address;

    public LocationDTO() {
    }

    public LocationDTO(String idLocation, String address) {
        this.idLocation = idLocation;
        this.address = address;
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
}