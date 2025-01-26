package com.natursalas.natursalassystem.model.dto;

public class LocationDTO {
    private String idLocation;
    private String locationName;
    private String address;
    private String city;

    public LocationDTO() {
    }

    public LocationDTO(String idLocation, String locationName, String address, String city) {
        this.idLocation = idLocation;
        this.locationName = locationName;
        this.address = address;
        this.city = city;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}