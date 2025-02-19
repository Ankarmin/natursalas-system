package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.LocationDAO;
import com.natursalas.natursalassystem.model.dto.LocationDTO;

import java.sql.Connection;
import java.util.List;

public class LocationService {
    private final LocationDAO locationDAO;

    public LocationService(Connection connection) {
        this.locationDAO = new LocationDAO(connection);
    }

    public boolean addLocation(LocationDTO newLocation) {
        return locationDAO.addLocation(newLocation);
    }

    public boolean updateLocation(LocationDTO updatedLocation) {
        return locationDAO.updateLocation(updatedLocation);
    }

    public boolean deleteLocation(String locationId) {
        return locationDAO.deleteLocation(locationId);
    }

    public LocationDTO getLocation(String locationId) {
        return locationDAO.getLocation(locationId);
    }

    public List<LocationDTO> getAllLocations() {
        return locationDAO.getAllLocations();
    }
}