package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.LocationDTO;

import java.util.List;

public interface ILocationDAO {
    boolean addLocation(LocationDTO newLocation);
    
    boolean updateLocation(LocationDTO updatedLocation);

    boolean deleteLocation(String locationId);

    LocationDTO getLocation(String locationId);

    List<LocationDTO> getAllLocations();
}